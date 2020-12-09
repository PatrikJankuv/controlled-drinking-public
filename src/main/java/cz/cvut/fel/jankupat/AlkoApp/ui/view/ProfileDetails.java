package cz.cvut.fel.jankupat.AlkoApp.ui.view;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.plotoptions.builder.BarBuilder;
import com.github.appreciated.apexcharts.config.tooltip.builder.YBuilder;
import com.github.appreciated.apexcharts.config.yaxis.builder.TitleBuilder;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.ProfileDrinkItemStatsAdapter;
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
import java.util.*;

/**
 * The type Calendar view.
 *
 * @author Patrik Jankuv
 * @created 11 /15/2020
 */
@PageTitle("Profile details")
@EnableVaadin({"com.example.application", "org.vaadin.stefan"})
@Route(value = "calendar", layout = MainLayout.class)
public class ProfileDetails extends VerticalLayout implements HasUrlParameter<Integer> {

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
    public ProfileDetails(ProfileService profileService, DrinkItemService service, DayService dayService) {
        this.profileService = profileService;
        this.drinkItemService = service;
        this.dayService = dayService;
    }

    private Component feelingsDetailsGraph(){
        Profile profile = profileService.find(profileId);
        TreeMap<String, Integer> feelings = dayService.getReflectionsForProfile(profile);

        String[] y = feelings.keySet().toArray(new String[0]);
        Integer[] data = feelings.values().toArray(new Integer[0]);
        ApexCharts barChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get()
                        .withType(Type.bar)
                        .build())
                .withPlotOptions(PlotOptionsBuilder.get()
                        .withBar(BarBuilder.get()
                                .withHorizontal(false)
                                .withColumnWidth("55%")
                                .build())
                        .build())
                .withDataLabels(DataLabelsBuilder.get()
                        .withEnabled(false).build())
                .withStroke(StrokeBuilder.get()
                        .withShow(true)
                        .withWidth(2.0)
                        .withColors("transparent")
                        .build())
                .withSeries(
                        new Series<>("Feeling", data))
                .withColors("#fed766")
                .withYaxis(YAxisBuilder.get()
                        .withTitle(TitleBuilder.get()
                                .withText("count")
                                .build())
                        .build())
                .withXaxis(XAxisBuilder.get().withCategories(y).build())
                .withFill(FillBuilder.get()
                        .withOpacity(1.0).build())
                .withTooltip(TooltipBuilder.get()
                        .withY(YBuilder.get()
                                .withFormatter("function (val) {\n" + // Formatter currently not yet working
                                        "return \" \" + val + \" \"\n" +
                                        "}").build())
                        .build())
                .build();
        add(barChart);
        setWidth("100%");

//        barChart.add(barChart);
        return barChart;
    }

    private Component profileDetails() {
        VerticalLayout verticalLayout = new VerticalLayout();
        Profile profile = profileService.find(profileId);
        verticalLayout.add(new H2("Profile details"));
        verticalLayout.add(new Label("Name: " + profile.getName()));
        verticalLayout.add(new Label("Age: " + profile.getAge()));
        verticalLayout.add(new Label("Gender: " + profile.getGender()));
        verticalLayout.add(new Label("Weight: " + profile.getWeight() + " kg"));
        verticalLayout.add(new Label("Height: " + profile.getHeight() + " cm"));
        verticalLayout.add(new Label("Smoker: " + profile.getSmoker()));
        return verticalLayout;
    }

    private Component favoriteDrinks(){
        List<ProfileDrinkItemStatsAdapter> items = drinkItemService.getProfileDrinks(this.profileService.find(profileId));
        HashMap<String, Long> planned = new HashMap<String, Long>();
        planned.put("BEER", 0l);
        planned.put("WINE", 0l);
        planned.put("FOOD", 0l);
        planned.put("SPIRIT", 0l);
        planned.put("NON_ALCOHOL", 0l);

        HashMap<String, Long> consumed = new HashMap<String, Long>();
        consumed.put("BEER", 0l);
        consumed.put("WINE", 0l);
        consumed.put("FOOD", 0l);
        consumed.put("SPIRIT", 0l);
        consumed.put("NON_ALCOHOL", 0l);


        for (ProfileDrinkItemStatsAdapter i : items) {
            if (i.isPlanned()) {
                planned.put(i.getType(), i.getCount());
            } else {
                consumed.put(i.getType(), i.getCount());
            }
        }
        ApexCharts barChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get()
                        .withType(Type.bar)
                        .build())
                .withPlotOptions(PlotOptionsBuilder.get()
                        .withBar(BarBuilder.get()
                                .withHorizontal(false)
                                .withColumnWidth("55%")
                                .build())
                        .build())
                .withDataLabels(DataLabelsBuilder.get()
                        .withEnabled(false).build())
                .withStroke(StrokeBuilder.get()
                        .withShow(true)
                        .withWidth(2.0)
                        .withColors("transparent")
                        .build())
                .withSeries(new Series<>("Planned", planned.get("BEER"), planned.get("WINE"), planned.get("NON_ALCOHOL"), planned.get("SPIRIT"), planned.get("FOOD")),
                        new Series<>("Consumed", consumed.get("BEER"), consumed.get("WINE"), consumed.get("NON_ALCOHOL"), consumed.get("SPIRIT"), consumed.get("FOOD")))
                .withColors("#2ab7ca", "#fe4a49")
                .withYaxis(YAxisBuilder.get()
                        .withTitle(TitleBuilder.get()
                                .withText("drink")
                                .build())
                        .build())
                .withXaxis(XAxisBuilder.get().withCategories("Beer", "Wine", "Free", "Spirit", "Food").build())
                .withFill(FillBuilder.get()
                        .withOpacity(1.0).build())
                .withTooltip(TooltipBuilder.get()
                        .withY(YBuilder.get()
                                .withFormatter("function (val) {\n" + // Formatter currently not yet working
                                        "return \" \" + val + \" \"\n" +
                                        "}").build())
                        .build())
                .build();
        add(barChart);
        setWidth("100%");

        return barChart;
    };

    private Component graphsLayer() {
        VerticalLayout graph = new VerticalLayout(new Label("Favorite drinks of user"));
        graph.add(favoriteDrinks());
        graph.add(new Label("Feelings"));
        graph.add(feelingsDetailsGraph());
        return graph;
    }

    //todo
    private Component configName() {

        Profile profile = this.profileService.find(profileId);

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
                refreshCalendar(dt);
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

    private void refreshCalendar(LocalDate dateTime) {
        List<DrinkItem> planned = new LinkedList<>();
        List<DrinkItem> drank = new LinkedList<>();

        List<DrinkItem> items = drinkItemService.getProfileDrinksForSpecificDay(profileService.find(profileId), dateTime);
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


    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer profileId) {
        this.profileId = profileId;

        HorizontalLayout profileDetails = new HorizontalLayout();
        profileDetails.add(profileDetails(), graphsLayer());
        profileDetails.expand(profileDetails);
        profileDetails.setWidthFull();
        add(profileDetails);

        VerticalLayout verticalFeelings = new VerticalLayout(new H3("Feelings"), feelings);
        HorizontalLayout top = new HorizontalLayout(dayToolbar(), verticalFeelings);
        top.setWidthFull();
        add(top);


        HorizontalLayout horizontalLayout = new HorizontalLayout(configPlanned(), configureDrank());

        suc.setColor("#28965a");
        horizontalLayout.setWidthFull();
        add(horizontalLayout);
    }
}
