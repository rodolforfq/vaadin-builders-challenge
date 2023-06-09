package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.builderschallenge.uimodel.ScheduleMetrics;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleSection.class);

    private final transient ScheduleMetrics scheduleMetrics;

    public ScheduleSection(DashboardService dashboardService) {
        super("Schedule");

        scheduleMetrics = dashboardService.fetchScheduleMetrics();
        LOG.info("ScheduleMetrics: {}", scheduleMetrics);

        addTile(createEventsWidget());
    }

    private Component createEventsWidget() {
        return ChartFactory.createNumber("Events",
                "The number of scheduled calendar events",
                scheduleMetrics.eventCount());
    }
}
