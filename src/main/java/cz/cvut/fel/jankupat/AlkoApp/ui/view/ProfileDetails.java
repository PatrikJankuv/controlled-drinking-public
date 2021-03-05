package cz.cvut.fel.jankupat.AlkoApp.ui.view;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.legend.HorizontalAlign;
import com.github.appreciated.apexcharts.config.legend.Position;
import com.github.appreciated.apexcharts.config.plotoptions.builder.BarBuilder;
import com.github.appreciated.apexcharts.config.responsive.builder.OptionsBuilder;
import com.github.appreciated.apexcharts.config.stroke.Curve;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.github.appreciated.apexcharts.config.tooltip.builder.YBuilder;
import com.github.appreciated.apexcharts.config.yaxis.builder.TitleBuilder;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
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
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private HorizontalLayout alcoholDayLevel = new HorizontalLayout();
    private VerticalLayout alcoholTimeLine = new VerticalLayout();

    private ProfileService profileService;
    private DrinkItemService drinkItemService;
    private DayService dayService;

    private HorizontalLayout feelings = new HorizontalLayout();

    private Label result = new Label();
    private Icon suc = new Icon(VaadinIcon.SMILEY_O);
    private Icon fail = new Icon(VaadinIcon.FROWN_O);
    private Label username = new Label();

    private static LocalDate sinceTimeLine;
    private static LocalDate toTimeLine;
    private static ApexCharts barChart;

    Logger logger = Logger.getAnonymousLogger();
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

    private Component feelingsDetailsGraph() {
        Profile profile = profileService.find(profileId);
        TreeMap<String, Integer> feelings = dayService.getReflectionsForProfile(profile);


        String[] y = feelings.keySet().toArray(new String[0]);
        Integer[] data = feelings.values().toArray(new Integer[0]);
        Double[] doubles = new Double[11];
        Arrays.fill(doubles, 0.0);

        for (int i = 0; i < data.length; i++) {
            if(data[i] != null){
                doubles[i] = Double.valueOf(data[i]);
            }

        }

// feelings as barchar
//        ApexCharts barChart = ApexChartsBuilder.get()
//                .withChart(ChartBuilder.get()
//                        .withType(Type.bar)
//                        .build())
//                .withPlotOptions(PlotOptionsBuilder.get()
//                        .withBar(BarBuilder.get()
//                                .withHorizontal(false)
//                                .withColumnWidth("55%")
//                                .build())
//                        .build())
//                .withDataLabels(DataLabelsBuilder.get()
//                        .withEnabled(false).build())
//                .withStroke(StrokeBuilder.get()
//                        .withShow(true)
//                        .withWidth(2.0)
//                        .withColors("transparent")
//                        .build())
//                .withSeries(
//                        new Series<>("Feeling", data))
//                .withColors("#fed766")
//                .withYaxis(YAxisBuilder.get()
//                        .withTitle(TitleBuilder.get()
//                                .withText("count")
//                                .build())
//                        .build())
//                .withXaxis(XAxisBuilder.get().withCategories(y).build())
//                .withFill(FillBuilder.get()
//                        .withOpacity(1.0).build())
//                .withTooltip(TooltipBuilder.get()
//                        .withY(YBuilder.get()
//                                .withFormatter("function (val) {\n" + // Formatter currently not yet working
//                                        "return \" \" + val + \" \"\n" +
//                                        "}").build())
//                        .build())
//                .build();

        ApexCharts barChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get().withType(Type.pie).build())
                .withLabels(y)
                .withLegend(LegendBuilder.get()
                        .withPosition(Position.right)
                        .build())
                .withSeries(doubles[0], doubles[1], doubles[2], doubles[3], doubles[4], doubles[5], doubles[6], doubles[7], doubles[8], doubles[9], doubles[10])
                .withResponsive(ResponsiveBuilder.get()
                        .withBreakpoint(480.0)
                        .withOptions(OptionsBuilder.get()
                                .withLegend(LegendBuilder.get()
                                        .withPosition(Position.bottom)
                                        .build())
                                .build())
                        .build())
                .build();
        barChart.setColors("#03071E", "#370617", "#6A040F", "#9D0208", "#9D0208", "#DC2F02", "#DC2F02", "#E85D04", "#F48C06", "#FAA307", "#FFBA08");
        setWidth("100%");

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

    private Component favoriteDrinks() {
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
    }

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
        drunk.setColumns("name", "drinkType", "price", "amount", "count", "alcohol");
        drunk.getColumns().forEach(col -> col.setAutoWidth(true));

        VerticalLayout verticalLayout = new VerticalLayout(h2Planned, drunk);
        verticalLayout.setWidthFull();
        return verticalLayout;
    }

    private Component configPlanned() {
        H4 h2Planned = new H4("Planned");

        planned.addClassName("contact-grid");
        planned.setWidthFull();
        planned.setColumns("name", "drinkType", "price", "amount", "count", "alcohol");
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
        alcoholConsumedDuringDayGraphConfig(drank);
        this.planned.setItems(planned);
        this.drunk.setItems(drank);
    }

    private void alcoholConsumedDuringDayGraphConfig(List<DrinkItem> drank) {

        ArrayList<DrinkItem>[] itemsByHour = new ArrayList[24];

        for (int i = 0; i < 24; i++) {
            itemsByHour[i] = new ArrayList<DrinkItem>();
        }

        //divide drinks into hours
        drank.forEach(drinkItem -> {
            LocalTime time = drinkItem.getDateTime();
            itemsByHour[time.getHour()].add(drinkItem);
        });

        double[] hoursAlcohol = new double[24];

        //count pure alcohol volume and add to array
        for (int i = 0; i < 24; i++) {
            double tempValue = 0.0;
            for (int j = 0; j < itemsByHour[i].size(); j++) {
                DrinkItem tempItem = itemsByHour[i].get(j);
                tempValue += tempItem.getCount() * tempItem.getAmount() * (tempItem.getAlcohol() * 0.01);
            }
            hoursAlcohol[i] = tempValue;
        }

//        for(double i : hoursAlcohol){
//            System.out.println(i);
//        }

        ApexCharts alcoholDayLevelChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get()
                        .withType(Type.area)
                        .withZoom(ZoomBuilder.get()
                                .withEnabled(false)
                                .build())
                        .build())
                .withDataLabels(DataLabelsBuilder.get()
                        .withEnabled(false)
                        .build())
                .withStroke(StrokeBuilder.get().withCurve(Curve.straight).build())
                .withSeries(new Series<>("Alc. volume in ml", hoursAlcohol[0], hoursAlcohol[1], hoursAlcohol[2], hoursAlcohol[3], hoursAlcohol[4], hoursAlcohol[5], hoursAlcohol[6], hoursAlcohol[7], hoursAlcohol[8], hoursAlcohol[9], hoursAlcohol[10], hoursAlcohol[11], hoursAlcohol[12], hoursAlcohol[13], hoursAlcohol[14], hoursAlcohol[15], hoursAlcohol[16], hoursAlcohol[17], hoursAlcohol[18], hoursAlcohol[19], hoursAlcohol[20], hoursAlcohol[21], hoursAlcohol[22], hoursAlcohol[23]))
                .withTitle(TitleSubtitleBuilder.get()
                        .withText("Alcohol consummation during a day")
                        .withAlign(Align.left).build())
                .withSubtitle(TitleSubtitleBuilder.get()
                        .withText("Alcohol volume in ml")
                        .withAlign(Align.left).build())
//                .withLabels(IntStream.range(1, 24).boxed().map(day -> LocalDate.of(2000, 1, day).toString()).toArray(String[]::new))
//                .withLabels("1", "2")
                .withXaxis(XAxisBuilder.get()
                        .withCategories("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00").build())
                .withYaxis(YAxisBuilder.get()
                        .withOpposite(true).build())
                .withLegend(LegendBuilder.get().withHorizontalAlign(HorizontalAlign.left).build())
                .build();
//        add(alcoholDayLevel);
        alcoholDayLevelChart.setWidth("100%");
        alcoholDayLevelChart.setHeight("400px");
        alcoholDayLevel.setWidthFull();
        alcoholDayLevel.removeAll();
        alcoholDayLevel.add(alcoholDayLevelChart);
    }

    private Component monthStats() {
        HorizontalLayout layout = new HorizontalLayout();

        // month picker
        ComboBox<Month> month = new ComboBox<>("Month");
        month.setItems(Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        month.setValue(LocalDate.now().getMonth());

        IntegerField fieldYear = new IntegerField("Year");
        fieldYear.setHasControls(true);
        fieldYear.setMin(2020);
        fieldYear.setValue(LocalDate.now().getYear());


        month.addValueChangeListener(valueChangeEvent -> {
            if (valueChangeEvent.getValue() == null) {
                layout.removeAll();
                LocalDate since = LocalDate.of(fieldYear.getValue(), LocalDate.now().getMonth(), 1);
                LocalDate to = LocalDate.of(fieldYear.getValue(), LocalDate.now().getMonth(), month.getValue().length(true));
                layout.add(month, fieldYear, gridRGG(since, to));
            } else {
                layout.removeAll();
                LocalDate since = LocalDate.of(fieldYear.getValue(), month.getValue(), 1);
                LocalDate to = LocalDate.of(fieldYear.getValue(), month.getValue(), month.getValue().length(true));
                layout.add(month, fieldYear, gridRGG(since, to));
            }
        });

        fieldYear.addValueChangeListener(integerFieldIntegerComponentValueChangeEvent -> {
            if (integerFieldIntegerComponentValueChangeEvent.getValue() == null) {
                layout.removeAll();
                LocalDate since = LocalDate.of(fieldYear.getValue(), LocalDate.now().getMonth(), 1);
                LocalDate to = LocalDate.of(fieldYear.getValue(), LocalDate.now().getMonth(), month.getValue().length(true));
                layout.add(month, fieldYear, gridRGG(since, to));
            } else {
                layout.removeAll();
                LocalDate since = LocalDate.of(fieldYear.getValue(), month.getValue(), 1);
                LocalDate to = LocalDate.of(fieldYear.getValue(), month.getValue(), month.getValue().length(true));
                layout.add(month, fieldYear, gridRGG(since, to));
            }
        });

        layout.add(month, fieldYear);
        return layout;
    }

    private Component gridRGG(LocalDate since, LocalDate to) {
        //grid with days
        Div form = new Div();
        form.setClassName("rowCellForm");
        List<Day> days = dayService.getForSpecifProfileDaysInRange(profileService.find(profileId), since, to);
        form.add(gridRGGLegend("Mo"));
        form.add(gridRGGLegend("Tu"));
        form.add(gridRGGLegend("We"));
        form.add(gridRGGLegend("Th"));
        form.add(gridRGGLegend("Fr"));
        form.add(gridRGGLegend("Sa"));
        form.add(gridRGGLegend("Su"));

        if (since.getDayOfWeek().getValue() != 7)
            for (int i = 1; i < since.getDayOfWeek().getValue(); i++) {
                form.add(greyThink("-"));
            }


        List<Component> daysList = new LinkedList<>();
        for (int i = 1; i <= to.getMonth().length(true); i++) {
            String d = to.getYear() + "-" + to.getMonth().getValue() + "-" + i;
            daysList.add(greyThink(d));
        }

        for (Day d : days) {
            int temp = d.getDateTime().getDayOfMonth();

            try {
                if (d.getPlanAccomplished()) {
                    String s = d.getDateTime() + "";
                    daysList.set(temp - 1, greenThink(s, String.valueOf(d.getDateTime().getDayOfMonth())));
                } else {
                    String s = d.getDateTime() + "";
                    daysList.set(temp - 1, redThink(s, String.valueOf(d.getDateTime().getDayOfMonth())));
                }
            } catch (NullPointerException ex) {
                logger.log(Level.FINE, "null pointer", ex);
            }
        }
        for (Component item : daysList) {
            form.add(item);
        }
        return form;
    }

    private Component greyThink(String date) {
        Div i1 = new Div(new Span("-"));
        i1.setClassName("itemForm formIcon empty");
        i1.setTitle(date);
        return i1;
    }

    private Component redThink(String date, String day) {
//        Div i1 = new Div(new Span("✗"));
        Div i1 = new Div(new Span(day));
        i1.setClassName("itemForm formIcon danger");
        i1.setTitle(date);
        return i1;
    }

    private Component greenThink(String date, String day) {
//        Div i1 = new Div(new Span("✓"));
        Div i1 = new Div(new Span(day));
        i1.setClassName("itemForm formIcon success");
        i1.setTitle(date);
        return i1;
    }

    private Component gridRGGLegend(String day) {
        Div i1 = new Div(new Span(day));
        i1.setClassName("itemForm formIcon info");
        i1.setTitle(day);
        return i1;
    }

    private void configAlcoholTimeLine() {
        alcoholTimeLine.setWidthFull();

        sinceTimeLine = LocalDate.of(2020, 10, 27);
        toTimeLine = LocalDate.of(2021, 2, 1);

        DatePicker sinceP = new DatePicker("Since");
        DatePicker toP = new DatePicker("To");

        sinceP.addValueChangeListener(datePickerLocalDateComponentValueChangeEvent -> {
            alcoholTimeLine.remove(barChart);
            sinceTimeLine = datePickerLocalDateComponentValueChangeEvent.getValue();
            configGraphInAlcoholTimeLine(sinceTimeLine, toTimeLine);
        });

        toP.addValueChangeListener(datePickerLocalDateComponentValueChangeEvent -> {
            alcoholTimeLine.remove(barChart);
            toTimeLine = datePickerLocalDateComponentValueChangeEvent.getValue();
            configGraphInAlcoholTimeLine(sinceTimeLine, toTimeLine);
        });

        HorizontalLayout layout = new HorizontalLayout(sinceP, toP);
        alcoholTimeLine.add(layout);


        configGraphInAlcoholTimeLine(sinceTimeLine, toTimeLine);
    }

    private void configGraphInAlcoholTimeLine(LocalDate since, LocalDate to) {
        ArrayList<Double> data = new ArrayList<Double>();
        ArrayList<String> days = new ArrayList<String>();
        Map<LocalDate, Double> t = dayService.getForProfileDaysInRangeAlcoholVolumeForEveryDay(profileService.find(1), since, to);

        for (LocalDate date = since; date.isBefore(to.plusDays(1)); date = date.plusDays(1)) {
            days.add(date.toString());
            if (t.get(date) == null) {
                data.add(0.0);
            } else {
                data.add(t.get(date));
            }
        }

        barChart = ApexChartsBuilder.get()
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
                .withSeries(new Series<>("Alc. volume (ml)", data.toArray()))
                .withYaxis(YAxisBuilder.get()
                        .withTitle(TitleBuilder.get()
                                .withText("alcohol (milliliters)")
                                .build())
                        .build())
                .withXaxis(XAxisBuilder.get()
                        .withCategories(days)
                        .build())
                .withFill(FillBuilder.get()
                        .withOpacity(1.0).build())
                .withTooltip(TooltipBuilder.get()
                        .withY(YBuilder.get()
                                .withFormatter("function (val) {\n" + // Formatter currently not yet working
                                        "return \" \" + val + \" ml \"\n" +
                                        "}").build())
                        .build())
                .build();
        alcoholTimeLine.add(barChart);
        barChart.setWidth("100%");
        barChart.setHeight("350px");

    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer profileId) {
        this.profileId = profileId;

        HorizontalLayout profileDetails = new HorizontalLayout();
        profileDetails.add(profileDetails(), graphsLayer());
        profileDetails.expand(profileDetails);
        profileDetails.setWidthFull();
        add(profileDetails);

        configAlcoholTimeLine();
        add(alcoholTimeLine);

        VerticalLayout verticalFeelings = new VerticalLayout(new H3("Feelings"), feelings);
        HorizontalLayout top = new HorizontalLayout(dayToolbar(), verticalFeelings);
        top.setWidthFull();
        add(monthStats());
        add(top);
        add(alcoholDayLevel);
        alcoholDayLevel.setWidthFull();

        HorizontalLayout horizontalLayout = new HorizontalLayout(configPlanned(), configureDrank());

        suc.setColor("#28965a");
        horizontalLayout.setWidthFull();
        add(horizontalLayout);

    }
}
