package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TownHallSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(TownHallSection.class);

    public TownHallSection(DashboardService dashboardService) {
        super("Town Hall");

        var townHallMetrics = dashboardService.fetchTownHallMetrics();
        LOG.info("TownHallMetrics: {}", townHallMetrics);

        addTile("Questions", createQuestionsWidget());
        addTile("Popularity", createPopularityWidget());
    }

    private static Component createQuestionsWidget() {
        return new Text("A pie chart showing the number of grouped questions by topic");
    }

    private static Component createPopularityWidget() {
        return new Text("A pie chart showing the number of votes per grouped question by topic");
    }
}
