package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.builderschallenge.uimodel.HackathonMetrics;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HackathonSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(HackathonSection.class);

    private final transient HackathonMetrics hackathonMetrics;

    public HackathonSection(DashboardService dashboardService) {
        super("Hackathon");

        hackathonMetrics = dashboardService.fetchHackathonMetrics();
        LOG.info("HackathonMetrics: {}", hackathonMetrics);

        addTile(createIdeasWidget());
        addTile(createPopularityWidget());
        addTile(createRegistrantsWidget());
    }

    private Component createIdeasWidget() {
        return ChartFactory.createNumber("Ideas",
                "The number of hackathon ideas",
                hackathonMetrics.ideaCount());
    }

    private Component createPopularityWidget() {
        return ChartFactory.createNumber("Popularity",
                "The number of hackathon upvotes",
                hackathonMetrics.upvoteCount());
    }

    private Component createRegistrantsWidget() {
        return ChartFactory.createNumber("Registrants",
                "The number of hackathon registrants",
                hackathonMetrics.registrantCount());
    }
}
