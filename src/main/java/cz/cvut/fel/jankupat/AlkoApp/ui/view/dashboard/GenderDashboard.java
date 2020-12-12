package cz.cvut.fel.jankupat.AlkoApp.ui.view.dashboard;

/**
 * @author Patrik Jankuv
 * @created 11/14/2020
 */

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.legend.Position;
import com.github.appreciated.apexcharts.config.plotoptions.builder.BarBuilder;
import com.github.appreciated.apexcharts.config.responsive.builder.OptionsBuilder;
import com.github.appreciated.apexcharts.config.tooltip.builder.YBuilder;
import com.github.appreciated.apexcharts.config.yaxis.builder.TitleBuilder;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.ProfileDrinkItemStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.ProfileStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.service.DrinkItemService;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Gender dashboard.
 */
@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
public class GenderDashboard extends VerticalLayout {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private ProfileService profileService;
    private DrinkItemService drinkItemService;
    private Map<String, Integer> genderStats;

    /**
     * Instantiates a new Gender dashboard.
     *
     * @param profileService the profile service
     */
    public GenderDashboard(ProfileService profileService, DrinkItemService drinkItemService) {
        this.profileService = profileService;
        this.drinkItemService = drinkItemService;
        genderStats = profileService.getGenderStats();

        HorizontalLayout layout = new HorizontalLayout(pieChartExample(), pieSmokerChartExample());
        layout.setWidthFull();
        add(layout);
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        List<ProfileStatsAdapter> stats = profileService.getStats();
        for(ProfileStatsAdapter item : stats){
            add(new Label(item.getGender()+": avg. age " + df2.format(item.getAvgAge()) + ", avg. weight " +  df2.format(item.getAvgWeigh()) + " kg, avg. height " +  df2.format(item.getAvgHeight()) + " cm"));
        }
        favoriteDrinks();
    }

    private Component getContactStats() {
        Span stats = new Span(profileService.getGenderStats() + " contacts");
        stats.addClassName("contact-stats");
        return stats;
    }


    private Component pieChartExample() {
        Span stats = new Span("Gender");
        ApexCharts pieChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get().withType(Type.pie).build())
                .withLabels("Male", "Female", "Other")
                .withLegend(LegendBuilder.get()
                        .withPosition(Position.right)
                        .build())
                .withSeries(Double.valueOf(genderStats.get("Muž")), Double.valueOf(genderStats.get("Žena")), Double.valueOf(genderStats.get("Jiné")))
                .withResponsive(ResponsiveBuilder.get()
                        .withBreakpoint(480.0)
                        .withOptions(OptionsBuilder.get()
                                .withLegend(LegendBuilder.get()
                                        .withPosition(Position.bottom)
                                        .build())
                                .build())
                        .build())
                .build();
        pieChart.setColors("#2ab7ca", "#fe4a49", "#fed766");
//        setWidth("120%");
        pieChart.setWidthFull();

        VerticalLayout verL = new VerticalLayout(stats, pieChart);
        return verL;
    }

    /**
     * Pie smoker chart example component.
     *
     * @return the component
     */
    public Component pieSmokerChartExample() {
        Span stats = new Span("Smoker");
        ApexCharts pieChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get().withType(Type.pie).build())
                .withLabels("Yes", "No", "Occasionally")
                .withLegend(LegendBuilder.get()
                        .withPosition(Position.right)
                        .build())
                .withSeries(Double.valueOf(genderStats.get("smoke")), Double.valueOf(genderStats.get("nosmoke")), Double.valueOf(genderStats.get("ocas_smoke")))
                .withResponsive(ResponsiveBuilder.get()
                        .withBreakpoint(480.0)
                        .withOptions(OptionsBuilder.get()
                                .withLegend(LegendBuilder.get()
                                        .withPosition(Position.bottom)
                                        .build())
                                .build())
                        .build())
                .build();
        pieChart.setColors("#2ab7ca", "#fe4a49", "#fed766");
        pieChart.setWidthFull();
        VerticalLayout verL = new VerticalLayout(stats, pieChart);
        return verL;
    }

    private void favoriteDrinks(){
        List<ProfileDrinkItemStatsAdapter> items = drinkItemService.getStatsAboutAllProfiles();
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
        barChart.setWidth("75%");
        add(barChart);
    };


}
