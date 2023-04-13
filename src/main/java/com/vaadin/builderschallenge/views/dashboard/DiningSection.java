package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.builderschallenge.uimodel.DiningMetrics;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiningSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(DiningSection.class);

    private final transient DiningMetrics diningMetrics;

    public DiningSection(DashboardService dashboardService) {
        super("Dining");

        diningMetrics = dashboardService.fetchDiningMetrics();
        LOG.info("DiningMetrics: {}", diningMetrics);

        addTile(createDiningWidget());
        addTile(createPreferencesWidget());
        addTile(createRestrictionsWidget());
    }

    private Component createDiningWidget() {
        return ChartFactory.createSolidGauge("Dining",
                "The number of meals reserved vs. number possible",
                diningMetrics.mealsReservedCount(), diningMetrics.mealsPossibleCount());
    }

    private Component createPreferencesWidget() {
        return ChartFactory.createSolidGauge("Preferences",
                "The number of diners with food preferences vs. total diners",
                diningMetrics.foodPreferenceCount(), diningMetrics.dinerCount());
    }

    private Component createRestrictionsWidget() {
        return ChartFactory.createSolidGauge("Restrictions",
                "The number of diners with food restrictions vs. total diners",
                diningMetrics.foodRestrictionCount(), diningMetrics.dinerCount());
    }
}
