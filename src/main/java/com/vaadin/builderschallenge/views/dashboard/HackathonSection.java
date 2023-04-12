package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HackathonSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(HackathonSection.class);

    public HackathonSection(DashboardService dashboardService) {
        super("Hackathon");

        var hackathonMetrics = dashboardService.fetchHackathonMetrics();
        LOG.info("HackathonMetrics: {}", hackathonMetrics);

        addTile("Ideas", createIdeasWidget());
        addTile("Popularity", createPopularityWidget());
        addTile("Registrants", createRegistrantsWidget());
    }

    private static Component createIdeasWidget() {
        return new Text("A single number showing the number of hackathon ideas");
    }

    private static Component createPopularityWidget() {
        return new Text("A single number showing the number of hackathon upvotes");
    }

    private static Component createRegistrantsWidget() {
        return new Text("A single number showing the number of hackathon registrants");
    }
}
