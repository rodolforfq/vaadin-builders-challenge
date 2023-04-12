package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
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
        var chart = new Chart(ChartType.PIE);
        chart.getConfiguration().setTitle("Questions");
        chart.getConfiguration().setSubTitle("The number of grouped questions by topic");

        return chart;
    }

    private Component createPopularityWidget() {
        var chart = new Chart(ChartType.PIE);
        chart.getConfiguration().setTitle("Popularity");
        chart.getConfiguration().setSubTitle("The number of votes per grouped question by topic");

        return chart;
    }
}
