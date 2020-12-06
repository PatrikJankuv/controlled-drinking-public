package cz.cvut.fel.jankupat.AlkoApp.ui.view.dashboard;

/**
 * @author Patrik Jankuv
 * @created 11/14/2020
 */

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.ChartBuilder;
import com.github.appreciated.apexcharts.config.builder.LegendBuilder;
import com.github.appreciated.apexcharts.config.builder.ResponsiveBuilder;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.legend.Position;
import com.github.appreciated.apexcharts.config.responsive.builder.OptionsBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.ProfileStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;

import java.text.DecimalFormat;
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
    private Map<String, Integer> genderStats;

    /**
     * Instantiates a new Gender dashboard.
     *
     * @param profileService the profile service
     */
    public GenderDashboard(ProfileService profileService) {
        this.profileService = profileService;
        genderStats = profileService.getGenderStats();

//        VerticalLayout genders = new VerticalLayout(new Span("Pohlaví"), pieChartExample());
//        VerticalLayout smoker = new VerticalLayout(new Span("Kuřáci"), pieSmokerChartExample());
//        setFlexGrow(1, genders);
////        setFlexGrow(1, smoker);
////        add(genders);
////        add(smoker);
//        Div content = new Div(pieSmokerChartExample(), pieSmokerChartExample());
//        add(content);

        HorizontalLayout layout = new HorizontalLayout(pieChartExample(), pieSmokerChartExample());
        layout.setWidthFull();
        add(layout);
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        List<ProfileStatsAdapter> stats = profileService.getStats();
        for(ProfileStatsAdapter item : stats){
            add(new Label(item.getGender()+": avg. age " + df2.format(item.getAvgAge()) + ", avg. weight " +  df2.format(item.getAvgWeigh()) + " kg, avg. height " +  df2.format(item.getAvgHeight()) + " cm"));
        }
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


}
