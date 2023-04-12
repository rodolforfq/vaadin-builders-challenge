package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiningSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(DiningSection.class);

    public DiningSection(DashboardService dashboardService) {
        super("Dining");

        var diningMetrics = dashboardService.fetchDiningMetrics();
        LOG.info("DiningMetrics: {}", diningMetrics);

        addTile(createDiningWidget());
        addTile(createPreferencesWidget());
        addTile(createRestrictionsWidget());
    }

    private Component createDiningWidget() {
        var chart = new Chart(ChartType.GAUGE);
        chart.getConfiguration().setTitle("Dining");
        chart.getConfiguration().setSubTitle("The number of meals reserved vs. number possible");

        return chart;
    }

    private Component createPreferencesWidget() {
        var chart = new Chart(ChartType.GAUGE);
        chart.getConfiguration().setTitle("Preferences");
        chart.getConfiguration().setSubTitle("The number of diners with food preferences vs. total diners");

        return chart;
    }

    private Component createRestrictionsWidget() {
        var chart = new Chart(ChartType.GAUGE);
        chart.getConfiguration().setTitle("Restrictions");
        chart.getConfiguration().setSubTitle("The number of diners with food restrictions vs. total diners");

        return chart;
    }
}
