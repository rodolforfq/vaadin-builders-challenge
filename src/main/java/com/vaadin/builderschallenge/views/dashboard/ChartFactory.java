package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;

import java.util.Arrays;

public final class ChartFactory {

    private ChartFactory() {
        // factory class - do not instantiate
    }


    public static Chart createSolidGauge(String title, String subtitle, int count, int total) {
        var chart = new Chart(ChartType.SOLIDGAUGE);

        var chartConfiguration = chart.getConfiguration();
        chartConfiguration.setTitle(title);
        chartConfiguration.setSubTitle(subtitle);

        var background = new Background();
        background.setShape(BackgroundShape.ARC);
        background.setInnerRadius("85%");

        var pane = chartConfiguration.getPane();
        pane.setStartAngle(-135);
        pane.setEndAngle(135);
        pane.setBackground(background);

        var yaxis = new YAxis();
//        yaxis.setTitle("Y-Axis");
//        yaxis.getTitle().setY(-80); // Move 80 px upwards from center

        // The limits are mandatory
        yaxis.setMin(0);
        yaxis.setMax(total);
        // Configure ticks and labels
        yaxis.setTickInterval(total);  // At 0, 100, and 200
        yaxis.setMinorTickInterval("null");
//        yaxis.getLabels().setY(-16); // Move 16 px upwards
        yaxis.setGridLineWidth(0); // Disable grid

        chartConfiguration.addyAxis(yaxis);

        var options = new PlotOptionsSolidgauge();

        // Move the value display box at the center a bit higher
        var dataLabels = new DataLabels();
        dataLabels.setY(-20);
        options.setDataLabels(dataLabels);

        chartConfiguration.setPlotOptions(options);

        var series = new ListSeries(count);
        chartConfiguration.addSeries(series);
//        series.setyAxis(yaxis);

        return chart;
    }


    public static Chart createPie(String title, String subtitle, PieSlice... pieSlices) {
//        var total = Arrays.stream(pieSlices).map(PieSlice::value).reduce(0, Integer::sum);

        var chart = new Chart(ChartType.PIE);

        var options = new PlotOptionsPie();
        options.setInnerSize("50%");
        options.setSize("65%");  // Default
        options.setCenter("50%", "50%"); // Default

        var series = new DataSeries();
        Arrays.stream(pieSlices)
                .map(pieSlice -> new DataSeriesItem(pieSlice.label(), pieSlice.value()))
                .forEach(series::add);

        var chartConfiguration = chart.getConfiguration();
        chartConfiguration.setTitle(title);
        chartConfiguration.setSubTitle(subtitle);
        chartConfiguration.setPlotOptions(options);
        chartConfiguration.addSeries(series);

        return chart;
    }

    public static record PieSlice(
            String label,
            int value
    ) {
    }
}
