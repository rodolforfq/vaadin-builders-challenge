package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.builderschallenge.uimodel.HackathonMetrics;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HackathonSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(HackathonSection.class);

    private final transient HackathonMetrics hackathonMetrics;

    public HackathonSection(DashboardService dashboardService) {
        super("Hackathon");

        hackathonMetrics = dashboardService.fetchHackathonMetrics();
        LOG.info("HackathonMetrics: {}", hackathonMetrics);

        addTile("Ideas", createIdeasWidget());
        addTile("Popularity", createPopularityWidget());
        addTile("Registrants", createRegistrantsWidget());
    }

    private Component createIdeasWidget() {
        return new Text("A single number showing the number of hackathon ideas");
    }

    private Component createPopularityWidget() {
        return new Text("A single number showing the number of hackathon upvotes");
    }

    private Component createRegistrantsWidget() {
        return new Text("A single number showing the number of hackathon registrants");
    }
}
