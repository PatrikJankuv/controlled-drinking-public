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
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.service.DayService;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Plane chart.
 *
 * @author Patrik Jankuv
 * @created 11 /21/2020
 */
@PageTitle("Stats")
@Route(value = "plan", layout = MainLayout.class)
public class PlaneChart extends VerticalLayout {

    private DayService dayService;
    private ApexCharts lineChart;
    private VerticalLayout layoutWithChart = new VerticalLayout();
    private VerticalLayout filter = new VerticalLayout();
    private ComboBox<String> durationCombox = new ComboBox<>();

    /**
     * Instantiates a new Plane chart.
     *
     * @param dayService the day service
     */
    public PlaneChart(DayService dayService) {
        this.dayService = dayService;
        add(filter);
        filter.setVisible(false);
        HorizontalLayout tools = new HorizontalLayout();
        durationCombox.setLabel("Select interval");
        durationCombox.setItems("Last week", "Last month");
        durationCombox.setClearButtonVisible(true);

        durationCombox.addValueChangeListener(event -> {
            if (event.getValue().equals("Last month")) {
                if(!filter.isVisible())
                    lastMonth();
                layoutWithChart.removeAll();
                layoutWithChart.add(lineChart);
            } else {
                if(!filter.isVisible())
                    lastWeek();
                layoutWithChart.removeAll();
                layoutWithChart.add(lineChart);
            }
        });

        durationCombox.setValue("Last month");

        configFilter();
        filter.setVisible(false);


        Button showFilter = new Button("Show filter");
        Button hideFilter = new Button("Hide filter");
        hideFilter.addClickListener(buttonClickEvent -> {
            filter.setVisible(false);
            showFilter.setVisible(true);
            hideFilter.setVisible(false);
        });

        showFilter.addClickListener(buttonClickEvent -> {
            filter.setVisible(true);
            showFilter.setVisible(false);
            hideFilter.setVisible(true);
        });
        tools.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        layoutWithChart.add(lineChart);
        tools.add(durationCombox, showFilter, hideFilter);
        add(tools, layoutWithChart);
        hideFilter.setVisible(false);
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
        fromHeight.setHeight("75%");
        IntegerField toHeight = new IntegerField("To height");
        HorizontalLayout height = new HorizontalLayout(fromHeight, toHeight);
        HorizontalLayout physical = new HorizontalLayout(weight, new Span(" "), height);

        MultiselectComboBox<String> genderComboBox = new MultiselectComboBox();
        genderComboBox.setWidth("75%");
        genderComboBox.setHeight("75%");
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

        DatePicker sinceDatePicker = new DatePicker();
        sinceDatePicker.setLabel("Since a day");
        sinceDatePicker.setPlaceholder("Since");
        DatePicker toDatePicker = new DatePicker();
        toDatePicker.setLabel("To a day");
        toDatePicker.setPlaceholder("To");
        HorizontalLayout dates = new HorizontalLayout(sinceDatePicker, toDatePicker);

        Button button = new Button("Filter");
        button.addClickListener(buttonClickEvent -> {
            durationCombox.getValue();

            int period = 7;
            if(durationCombox.getValue().equals("Last month")){
                period = 30;
            }

            layoutWithChart.removeAll();
            configureChart(dayService.getStatsFilter(period, fromAge.getValue(), toAge.getValue(), genderComboBox.getSelectedItems(), smokerComboBox.getSelectedItems(), fromWeight.getValue(), toWeight.getValue(), fromHeight.getValue(), toHeight.getValue()), "Filtered data");

            layoutWithChart.add(lineChart);
        });

        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        filter.addClassNames("contact-grid");
        filter.setWidth("50%");
        filter.add(age, comboxes, physical, dates, button);
        filter.addClassName("label");
        filter.setHeight("75%");
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
        configureChart(dayService.getStats(7), "Trend the last week");
    }

    private void lastMonth() {
        configureChart(dayService.getStats(30), "Trend the last month");
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

