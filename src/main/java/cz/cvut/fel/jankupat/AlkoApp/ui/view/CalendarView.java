package cz.cvut.fel.jankupat.AlkoApp.ui.view;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.ChartBuilder;
import com.github.appreciated.apexcharts.config.builder.LegendBuilder;
import com.github.appreciated.apexcharts.config.builder.ResponsiveBuilder;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.legend.Position;
import com.github.appreciated.apexcharts.config.responsive.builder.OptionsBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.Reflection;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;
import cz.cvut.fel.jankupat.AlkoApp.service.DayService;
import cz.cvut.fel.jankupat.AlkoApp.service.DrinkItemService;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The type Calendar view.
 *
 * @author Patrik Jankuv
 * @created 11 /15/2020
 */
@PageTitle("Calendar")
@EnableVaadin({"com.example.application", "org.vaadin.stefan"})
@Route(value = "calendar", layout = MainLayout.class)
public class CalendarView extends VerticalLayout implements HasUrlParameter<Integer> {

    private Grid<DrinkItem> planned = new Grid<>(DrinkItem.class);
    private Grid<DrinkItem> drunk = new Grid<>(DrinkItem.class);

    private ProfileService profileService;
    private DrinkItemService drinkItemService;
    private DayService dayService;

    private HorizontalLayout feelings = new HorizontalLayout();

    private Label result = new Label();
    private Icon suc = new Icon(VaadinIcon.SMILEY_O);
    private Icon fail = new Icon(VaadinIcon.FROWN_O);
    private Label username = new Label();
    /**
     * The Text.
     */
    Span text = new Span();

    /**
     * The Profile id.
     */
    Integer profileId;

    /**
     * Instantiates a new Calendar view.
     *
     * @param profileService the profile service
     * @param service        the service
     * @param dayService     the day service
     */
    public CalendarView(ProfileService profileService, DrinkItemService service, DayService dayService) {
        this.profileService = profileService;
        this.drinkItemService = service;
        this.dayService = dayService;

        VerticalLayout verticalFeelings = new VerticalLayout(new H3("Feelings"), feelings);
        HorizontalLayout top = new HorizontalLayout(dayToolbar(), verticalFeelings);
        top.setWidthFull();
        add(top);


        HorizontalLayout horizontalLayout = new HorizontalLayout(configPlanned(), configureDrank());

        suc.setColor("#28965a");
        horizontalLayout.setWidthFull();
        add(horizontalLayout);
    }

    private Component configName() {

        System.out.println(this.profileId);
        Profile profile = this.profileService.find(1);

        username.setText("User " + profile.getName());
        return username;
    }

    private void configureFeelings(LocalDate dateTime) {
        Reflection reflection = dayService.getFeelingsForProfile(profileService.find(profileId), dateTime);
        feelings.removeAll();
        Set<FeelingsEnum> feelings = reflection.getFeelings();

        for (FeelingsEnum feel : feelings) {
            Label label = new Label(feel.toString());
            label.setClassName("label other");
            this.feelings.add(label);
        }
    }


    private Component dayToolbar() {

        DatePicker datePicker = new DatePicker();
        datePicker.setLabel("Select a day");
        datePicker.setPlaceholder("Date within " + "this month");
        datePicker.addValueChangeListener(event -> {
            if (event.getValue() == null) {
            } else {
                LocalDate dt = event.getValue();
                hry(dt);
                configureFeelings(dt);
                //todo
                configResult(dt);
            }
        });

//        text.setText(" success");
//        result.add(suc, fail, text);
        configName();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(datePicker, result);

        horizontalLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        return horizontalLayout;
    }

    private Component configureDrank() {
        H4 h2Planned = new H4("Consumed");

        drunk.addClassName("contact-grid");
        drunk.setWidthFull();
        drunk.setColumns("name", "drinkType", "price", "amount", "alcohol");
        drunk.getColumns().forEach(col -> col.setAutoWidth(true));

        VerticalLayout verticalLayout = new VerticalLayout(h2Planned, drunk);
        verticalLayout.setWidthFull();
        return verticalLayout;
    }

    private Component configPlanned() {
        H4 h2Planned = new H4("Planned");

        planned.addClassName("contact-grid");
        planned.setWidthFull();
        planned.setColumns("name", "drinkType", "price", "amount", "alcohol");
        planned.getColumns().forEach(col -> col.setAutoWidth(true));

        VerticalLayout verticalLayout = new VerticalLayout(h2Planned, planned);
        verticalLayout.setWidthFull();
        return verticalLayout;
    }

    private void configResult(LocalDate dateTime) {
        Day day = dayService.getDayForProfile(profileService.find(profileId), dateTime);

        result.removeAll();
        if (day.getPlanAccomplished()) {
            text.setText(" success");
            result.add(suc, text);
        } else {
            text.setText(" fail");
            result.add(fail, text);
        }

    }

    private void hry(LocalDate dateTime) {
        List<DrinkItem> planned = new LinkedList<>();
        List<DrinkItem> drank = new LinkedList<>();

        List<DrinkItem> items = drinkItemService.getProfileDrinks(profileService.find(profileId), dateTime);
        for (DrinkItem item : items) {
            if (item.getPlanned()) {
                planned.add(item);
            } else {
                drank.add(item);
            }
        }
        this.planned.setItems(items);
        this.drunk.setItems(drank);
    }

    /**
     * Pie chart example component.
     *
     * @return the component
     */
    public Component pieChartExample() {
        Span stats = new Span("Favorite items");
        ApexCharts pieChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get().withType(Type.pie).build())
                .withLabels("BEER", "WINE", "SPIRIT", "FREE")
                .withLegend(LegendBuilder.get()
                        .withPosition(Position.right)
                        .build())
                .withSeries(32.0, 25.0, 17.0, 13.0)
                .withResponsive(ResponsiveBuilder.get()
                        .withBreakpoint(480.0)
                        .withOptions(OptionsBuilder.get()
                                .withLegend(LegendBuilder.get()
                                        .withPosition(Position.bottom)
                                        .build())
                                .build())
                        .build())
                .build();
        pieChart.setColors("#e76f51", "#f4a261", "#e9c46a", "#2a9d8f");
//        setWidth("120%");
        pieChart.setWidthFull();

        VerticalLayout verL = new VerticalLayout(stats, pieChart);
        return verL;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer profileId) {
        this.profileId = profileId;
    }
}
