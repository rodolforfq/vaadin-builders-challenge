package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationSection.class);

    public RegistrationSection(DashboardService dashboardService) {
        super("Registration");

        var registrationMetrics = dashboardService.fetchRegistrationMetrics();
        LOG.info("RegistrationMetrics: {}", registrationMetrics);


        addTile("Registration", createRegistrationWidget());
        addTile("Participation", createParticipationWidget());
    }

    private static Component createRegistrationWidget() {
        return new Text("A dial showing number of employees registered vs. total in company");
    }

    private static Component createParticipationWidget() {
        return new Text("A dial showing number of employee-days registered vs. number of employee-days possible (try to show party part of dial in a different color—or even each day's sum as a different color)");
    }
}
