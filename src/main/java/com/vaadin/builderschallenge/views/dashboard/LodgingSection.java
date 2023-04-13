package com.vaadin.builderschallenge.views.dashboard;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.builderschallenge.uimodel.LodgingMetrics;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.model.ListSeries;

public class LodgingSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(LodgingSection.class);

    private final transient LodgingMetrics lodgingMetrics;

    public LodgingSection(DashboardService dashboardService) {
        super("Lodging");

        lodgingMetrics = dashboardService.fetchLodgingMetrics();
        LOG.info("LodgingMetrics: {}", lodgingMetrics);

        addTile(createOccupancyWidget());
    }

    private Component createOccupancyWidget() {
        var chart = ChartFactory.createStackedPercentageColumn("Occupancy", "Occupancy Rate by Hotel",
                "Hotels", "Occupation Percentage", List.of("Sokos Kupittaa", "Scandic Julia", "Centro Hotel"));
        chart.getConfiguration().addSeries(new ListSeries("Available", 10, 8, 15));
        chart.getConfiguration().addSeries(new ListSeries("Booked", 5, 12, 9));
        return chart;
    }

}
