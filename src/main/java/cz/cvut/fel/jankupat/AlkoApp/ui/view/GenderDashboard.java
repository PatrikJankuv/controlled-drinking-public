package cz.cvut.fel.jankupat.AlkoApp.ui.view;

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
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;

import java.awt.*;
import java.util.Map;


@Route(value = "dashboard", layout = MainLayout.class)
public class GenderDashboard extends VerticalLayout {

    private ProfileService contactService;
    private Map<String, Integer> genderStats;

    public GenderDashboard(ProfileService contactService) {
        this.contactService = contactService;
        genderStats = contactService.getGenderStats();

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
    }

    private Component getContactStats() {
        Span stats = new Span(contactService.getGenderStats() + " contacts");
        stats.addClassName("contact-stats");
        return stats;
    }


    public Component pieChartExample() {
        Span stats = new Span("Pohlaví");
        ApexCharts pieChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get().withType(Type.pie).build())
                .withLabels("Muži", "Ženy", "Jiné")
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

    public Component pieSmokerChartExample() {
        Span stats = new Span("Kuřáci");
        ApexCharts pieChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get().withType(Type.pie).build())
                .withLabels("Áno", "Ne", "Občas")
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
