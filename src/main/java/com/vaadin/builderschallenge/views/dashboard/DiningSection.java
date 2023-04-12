package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiningSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(DiningSection.class);

    public DiningSection(DashboardService dashboardService) {
        super("Dining");

        var diningMetrics = dashboardService.fetchDiningMetrics();
        LOG.info("DiningMetrics: {}", diningMetrics);

        addTile("Dining", createDiningWidget());
        addTile("Preferences", createPreferencesWidget());
        addTile("Restrictions", createRestrictionsWidget());
    }

    private static Component createDiningWidget() {
        return new Text("A dial showing the number of meals reserved vs. number possible");
    }

    private static Component createPreferencesWidget() {
        return new Text("A dial showing the number of diners with food preferences vs. total diners");
    }

    private static Component createRestrictionsWidget() {
        return new Text("A dial showing the number of diners with food restrictions vs. total diners");
    }
}
