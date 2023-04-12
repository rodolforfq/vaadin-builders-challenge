package com.vaadin.builderschallenge.services;

import com.vaadin.builderschallenge.uimodel.*;

public interface DashboardService {

    DiningMetrics fetchDiningMetrics();

    HackathonMetrics fetchHackathonMetrics();

    LodgingMetrics fetchLodgingMetrics();

    RegistrationMetrics fetchRegistrationMetrics();

    ScheduleMetrics fetchScheduleMetrics();

    TownHallMetrics fetchTownHallMetrics();
}
