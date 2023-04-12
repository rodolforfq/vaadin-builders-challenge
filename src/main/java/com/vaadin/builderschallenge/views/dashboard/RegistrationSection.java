package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationSection.class);

    public RegistrationSection(DashboardService dashboardService) {
        super("Registration");

        var registrationMetrics = dashboardService.fetchRegistrationMetrics();
        LOG.info("RegistrationMetrics: {}", registrationMetrics);


        addTile(createRegistrationWidget());
        addTile(createParticipationWidget());
    }

    private Component createRegistrationWidget() {
        var chart = new Chart(ChartType.GAUGE);
        chart.getConfiguration().setTitle("Registration");
        chart.getConfiguration().setSubTitle("The number of employees registered vs. total in company");

        return chart;
    }

    // try to show party part of dial in a different color—or even each day's sum as a different color
    private Component createParticipationWidget() {
        var chart = new Chart(ChartType.GAUGE);
        chart.getConfiguration().setTitle("Participation");
        chart.getConfiguration().setSubTitle("The number of employee-days registered vs. number of employee-days possible");

        return chart;
    }
}
