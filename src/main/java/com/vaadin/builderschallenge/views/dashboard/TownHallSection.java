package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TownHallSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(TownHallSection.class);

    public TownHallSection(DashboardService dashboardService) {
        super("Town Hall");

        var townHallMetrics = dashboardService.fetchTownHallMetrics();
        LOG.info("TownHallMetrics: {}", townHallMetrics);

        addTile(createQuestionsWidget());
        addTile(createPopularityWidget());
    }

    private Component createQuestionsWidget() {
        return ChartFactory.createPie("Questions",
                "The number of grouped questions by topic",
                1, 2, 3);
    }

    private Component createPopularityWidget() {
        return ChartFactory.createPie("Popularity",
                "The number of votes per grouped question by topic",
                1, 2, 3);
    }
}
