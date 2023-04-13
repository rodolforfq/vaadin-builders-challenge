package com.vaadin.builderschallenge.views.dashboard;

import java.util.Arrays;
import java.util.Collection;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.AxisTitle;
import com.vaadin.flow.component.charts.model.Background;
import com.vaadin.flow.component.charts.model.BackgroundShape;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataLabels;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.PlotOptionsColumn;
import com.vaadin.flow.component.charts.model.PlotOptionsSolidgauge;
import com.vaadin.flow.component.charts.model.Stacking;
import com.vaadin.flow.component.charts.model.Tooltip;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.charts.model.style.SolidColor;

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

    public static Chart createStackedPercentageColumn(String title, String subtitle, String xAxisLabel, String yAxisLabel,
                                                      Collection<String> xAxisCategories) {
        var chart = new Chart(ChartType.COLUMN);

        var chartConfiguration = chart.getConfiguration();
        chartConfiguration.setTitle(title);
        chartConfiguration.setSubTitle(subtitle);

        var xAxis = new XAxis();
        xAxis.setCategories(xAxisCategories.toArray(new String[]{}));
        xAxis.setTitle(xAxisLabel);
        chartConfiguration.addxAxis(xAxis);

        var yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle(new AxisTitle(yAxisLabel));
        chartConfiguration.addyAxis(yAxis);

        var tooltip = new Tooltip();
        tooltip.setFormatter("function() { return '' + this.series.name + ': ' + this.y + ' (' + Math.round(this.percentage) + '%)'; }");
        chartConfiguration.setTooltip(tooltip);

        var plotOptions = new PlotOptionsColumn();
        plotOptions.setStacking(Stacking.PERCENT);
        chartConfiguration.setPlotOptions(plotOptions);

        return chart;
    }

    public static record PieSlice(
            String label,
            int value
    ) {
    }
    
}
