package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.Arrays;
import java.util.Collection;

public final class ChartFactory {

    private ChartFactory() {
        // factory class - do not instantiate
    }


    public static Component createNumber(String title, String subtitle, int value) {
        var titleSpan = new Span(title);
        titleSpan.addClassNames(LumoUtility.FontSize.LARGE);

        var subtitleSpan = new Span(subtitle);
        subtitleSpan.addClassNames(LumoUtility.FontSize.XSMALL,
                LumoUtility.FontWeight.LIGHT,
                LumoUtility.TextAlignment.CENTER);

        var numberSpan = new Span(Integer.toString(value));
        numberSpan.addClassNames(LumoUtility.FontWeight.EXTRABOLD,
                LumoUtility.TextColor.PRIMARY);
        numberSpan.getStyle().set("font-size", "120px");

        var layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(titleSpan);
        layout.add(subtitleSpan);
        layout.add(numberSpan);

        return layout;
    }


    public static Chart createSolidGauge(String title, String subtitle, int count, int total) {
        var chart = new Chart(ChartType.SOLIDGAUGE);

        var yaxis = new YAxis();
//        yaxis.setTitle("Y-Axis");
//        yaxis.getTitle().setY(-80); // Move 80 px upwards from center

        // The limits are mandatory
        yaxis.setMin(0);
        yaxis.setMax(total);
        // Configure ticks and labels
        yaxis.setTickInterval(total);
        yaxis.setMinorTickInterval("null");
        yaxis.setGridLineWidth(0); // Disable grid

        var series = new ListSeries(count);

        // Move the value display box at the center a bit higher
        var dataLabels = new DataLabels();
        dataLabels.setY(-20);

        var options = new PlotOptionsSolidgauge();
        options.setDataLabels(dataLabels);

        var chartConfiguration = chart.getConfiguration();
        chartConfiguration.setTitle(title);
        chartConfiguration.setSubTitle(subtitle);
        chartConfiguration.addyAxis(yaxis);
        chartConfiguration.addSeries(series);
        chartConfiguration.setPlotOptions(options);

        var background = new Background();
        background.setShape(BackgroundShape.ARC);
        background.setInnerRadius("60%");

        var pane = chartConfiguration.getPane();
        pane.setStartAngle(-135);
        pane.setEndAngle(135);
        pane.setBackground(background);

        return chart;
    }


    public static Chart createPie(String title, String subtitle, PieSlice... pieSlices) {
//        var total = Arrays.stream(pieSlices).map(PieSlice::value).reduce(0, Integer::sum);

        var chart = new Chart(ChartType.PIE);

        var options = new PlotOptionsPie();
        options.setInnerSize("50%");
        options.setSize("65%");

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
}
