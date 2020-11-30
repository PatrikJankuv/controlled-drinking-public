package cz.cvut.fel.jankupat.AlkoApp.ui.view;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.grid.builder.RowBuilder;
import com.github.appreciated.apexcharts.config.stroke.Curve;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.flow.component.Component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.service.DayService;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Patrik Jankuv
 * @created 11/21/2020
 */
@Route(value = "plan", layout = MainLayout.class)
public class PlaneChart extends VerticalLayout {

    private DayService dayService;
    private ApexCharts lineChart;
    private VerticalLayout layoutWithChart = new VerticalLayout();
    private VerticalLayout filter = new VerticalLayout();

    public PlaneChart(DayService dayService) {
        this.dayService = dayService;
        add(filter);
        filter.setVisible(false);
        HorizontalLayout tools = new HorizontalLayout();
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLabel("Select interval");
        comboBox.setItems("Last week", "Last month");
        comboBox.setClearButtonVisible(true);

        comboBox.addValueChangeListener(event -> {
            if (event.getValue().equals("Last month")) {
                lastMonth();
                layoutWithChart.removeAll();
                layoutWithChart.add(lineChart);
            } else {
                lastWeek();
                layoutWithChart.removeAll();
                layoutWithChart.add(lineChart);
            }
        });

        lastMonth();

//        add(configFilter());
        Button showFilter = new Button("Show filter");
        showFilter.addClickListener(buttonClickEvent -> {
            configFilter();
            showFilter.setDisableOnClick(true);
        });
        tools.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        layoutWithChart.add(lineChart);
        tools.add(comboBox, showFilter);
        add(tools, layoutWithChart);
//        add(configureChart());
    }

    private void configFilter() {
        IntegerField fromAge = new IntegerField("From age");
        IntegerField toAge = new IntegerField("To age");
        HorizontalLayout age = new HorizontalLayout(fromAge, toAge);

        IntegerField fromWeight = new IntegerField("From weight");
        IntegerField toWeight = new IntegerField("To weight");
        HorizontalLayout weight = new HorizontalLayout(fromWeight, toWeight);

        IntegerField fromHeight = new IntegerField("From height");
        IntegerField toHeight = new IntegerField("To height");
        HorizontalLayout height = new HorizontalLayout(fromHeight, toHeight);
        HorizontalLayout physical = new HorizontalLayout(weight, new Span(" "), height);

        MultiselectComboBox<String> genderComboBox = new MultiselectComboBox();
        genderComboBox.setWidth("100%");
        genderComboBox.setLabel("Select gender");
        genderComboBox.setPlaceholder("Choose...");
        genderComboBox.setItems("Male", "Female", "Other");
        genderComboBox.setClearButtonVisible(true);

        MultiselectComboBox<String> smokerComboBox = new MultiselectComboBox();
        smokerComboBox.setWidth("100%");
        smokerComboBox.setLabel("Select smoker");
        smokerComboBox.setPlaceholder("Choose...");
        smokerComboBox.setItems("Yes", "No", "Occasionally");
        smokerComboBox.setClearButtonVisible(true);
        HorizontalLayout comboxes = new HorizontalLayout(genderComboBox, smokerComboBox);

        Button button = new Button("Filter");
        button.addClickListener(buttonClickEvent -> {
            fromAge.getValue();
            toAge.getValue();
            genderComboBox.getSelectedItems();
            layoutWithChart.removeAll();
            configureChart(dayService.getStatsFilter(fromAge.getValue(), toAge.getValue(), genderComboBox.getSelectedItems(), smokerComboBox.getSelectedItems(), fromWeight.getValue(), toWeight.getValue(), fromHeight.getValue(), toHeight.getValue()), "Filtered data");

            layoutWithChart.add(lineChart);
        });

        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        filter.addClassNames("contact-grid");
        filter.setWidth("50%");
        filter.add(age, comboxes, physical, button);
        filter.addClassName("label");
        filter.setVisible(true);
    }

    private void writeDataToChart(ArrayList<Double> succ, ArrayList<Double> fail, ArrayList<String> days, String title) {
        lineChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get()
                        .withType(Type.line)
                        .withZoom(ZoomBuilder.get()
                                .withEnabled(false)
                                .build())
                        .build())
                .withStroke(StrokeBuilder.get()
                        .withCurve(Curve.straight)
                        .build())
                .withTitle(TitleSubtitleBuilder.get()
                        .withText(title)
                        .withAlign(Align.left)
                        .build())
                .withGrid(GridBuilder.get()
                        .withRow(RowBuilder.get()
                                .withColors("#f3f3f3", "transparent")
                                .withOpacity(0.5).build()
                        ).build())
                .withXaxis(XAxisBuilder.get()
                        .withCategories(days)
                        .build())
                .withSeries(new Series<>("Splneny plan", succ.toArray()), new Series<>("Nesplneny plan", fail.toArray()))
                .withColors("#8ac926", "#ff595e")
                .build();
        setWidth("100%");
    }

    private void lastWeek() {
        configureChart(dayService.getStats(), "Trend the last week");
    }

    private void lastMonth() {
        configureChart(dayService.getStats(), "Trend the last month");
    }

    /**
     * Has to convert data from service into two arrays
     *
     * @return Chart
     */
    private void configureChart(List<DayStatsAdapter> stats, String title) {

        ArrayList<Double> succ = new ArrayList<Double>();
        ArrayList<Double> fail = new ArrayList<Double>();
        ArrayList<String> days = new ArrayList<String>();

        for (DayStatsAdapter stat : stats) {
            boolean accomplished = stat.isPlan();

            if (accomplished) {
                succ.add((double) stat.getCount());
                if (stat.getDateTime() != null)
                    days.add(stat.getDateTime().toString());
            } else {
                fail.add((double) stat.getCount());
            }

        }
        writeDataToChart(succ, fail, days, title);
    }
}

