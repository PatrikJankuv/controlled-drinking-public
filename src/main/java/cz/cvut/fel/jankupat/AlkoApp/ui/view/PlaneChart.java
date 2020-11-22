package cz.cvut.fel.jankupat.AlkoApp.ui.view;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.grid.builder.RowBuilder;
import com.github.appreciated.apexcharts.config.legend.HorizontalAlign;
import com.github.appreciated.apexcharts.config.stroke.Curve;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.github.appreciated.apexcharts.config.xaxis.XAxisType;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.service.DayService;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Patrik Jankuv
 * @created 11/21/2020
 */
@Route(value = "plan", layout = MainLayout.class)
public class PlaneChart extends VerticalLayout {

    private DayService dayService;

    public PlaneChart(DayService dayService) {
        this.dayService = dayService;

        List<DayStatsAdapter> stats = dayService.getStats();

//        for (int i = 0; i < stats.size(); i++) {
//            System.out.println(stats.get(i).getCount() + " dt " + stats.get(i).getDateTime() + " plan " + stats.get(i).isPlan());
//        }

        add(configureChart());
    }

    private Component configureChart() {

        ApexCharts lineChart = ApexChartsBuilder.get()
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
                        .withText("Product Trends by Month")
                        .withAlign(Align.left)
                        .build())
                .withGrid(GridBuilder.get()
                        .withRow(RowBuilder.get()
                                .withColors("#f3f3f3", "transparent")
                                .withOpacity(0.5).build()
                        ).build())
                .withXaxis(XAxisBuilder.get()
                        .withCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep")
                        .build())
                .withSeries(new Series<>("Desktops", 10.0, 41.0, 35.0, 51.0, 49.0, 62.0, 69.0, 91.0, 148.0, 11.0, 72.8), new Series<>("Mobile", 11.0, 49.0, 15.0, 51.0, 49.0, 62.0, 26.0, 91.0, 18.0))
                .build();
        add(lineChart);
        setWidth("100%");

        return lineChart;
    }
}

