package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
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
        return ChartFactory.createSolidGauge("Dining",
                "The number of meals reserved vs. number possible",
                50,100);
    }

    private Component createPreferencesWidget() {
        return ChartFactory.createSolidGauge("Preferences",
                "The number of diners with food preferences vs. total diners",
                50, 100);
    }

    private Component createRestrictionsWidget() {
        return ChartFactory.createSolidGauge("Restrictions",
                "The number of diners with food restrictions vs. total diners",
                50, 100);
    }
}
