package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LodgingSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(LodgingSection.class);

    public LodgingSection(DashboardService dashboardService) {
        super("Lodging");

        var lodgingMetrics = dashboardService.fetchLodgingMetrics();
        LOG.info("LodgingMetrics: {}", lodgingMetrics);

        addTile("Occupancy", createOccupancyWidget());
    }

    private static Component createOccupancyWidget() {
        return new Text("A set of dials showing number of room-nights reserved vs. blocked by night");
    }
}
