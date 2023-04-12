package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.builderschallenge.uimodel.RegistrationMetrics;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationSection.class);
    private final transient RegistrationMetrics registrationMetrics;

    public RegistrationSection(DashboardService dashboardService) {
        super("Registration");

        registrationMetrics = dashboardService.fetchRegistrationMetrics();
        LOG.info("RegistrationMetrics: {}", registrationMetrics);

        addTile(createRegistrationWidget());
        addTile(createParticipationWidget());
    }

    private Component createRegistrationWidget() {
        var registrantCount = registrationMetrics.registrantCount();
        var employeeCount = registrationMetrics.employeeCount();

        return ChartFactory.createSolidGauge("Registration",
                "The number of employees registered vs. total in company",
                registrantCount, employeeCount);
    }

    // TODO try to show party part of gauge in a different color—or even each day's sum as a different color
    private Component createParticipationWidget() {
        var registrantDayCount = registrationMetrics.registrantDayCount();
        var employeeDayCount = registrationMetrics.employeeDayCount();

        return ChartFactory.createSolidGauge("Participation",
                "The number of employee-days registered vs. number of employee-days possible",
                registrantDayCount, employeeDayCount);
    }
}
