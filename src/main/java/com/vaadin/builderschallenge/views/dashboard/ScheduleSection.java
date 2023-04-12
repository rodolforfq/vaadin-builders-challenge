package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleSection.class);

    public ScheduleSection(DashboardService dashboardService) {
        super("Schedule");

        var scheduleMetrics = dashboardService.fetchScheduleMetrics();
        LOG.info("ScheduleMetrics: {}", scheduleMetrics);

        addTile("Events", createEventsWidget());
    }

    private static Component createEventsWidget() {
        return new Text("A single number showing the number of scheduled calendar events");
    }
}
