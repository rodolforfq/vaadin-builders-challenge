package com.vaadin.builderschallenge.jpaservices;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.builderschallenge.uimodel.*;
import com.vaadin.builderschallenge.uimodel.TownHallMetrics.TopicGroupedQuestions;
import com.vaadin.builderschallenge.uimodel.TownHallMetrics.TopicVotes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaDashboardService implements DashboardService {

    @Override
    public DiningMetrics fetchDiningMetrics() {
        // TODO fetch these results via a JPA call and repackage the entities into UI Model records
        return new DiningMetrics(260, 380, 11, 14, 73);
    }

    @Override
    public HackathonMetrics fetchHackathonMetrics() {
        // TODO fetch these results via a JPA call and repackage the entities into UI Model records
        return new HackathonMetrics(7, 30, 38);
    }

    @Override
    public LodgingMetrics fetchLodgingMetrics() {
        // TODO fetch these results via a JPA call and repackage the entities into UI Model records
        return new LodgingMetrics(110, 175);
    }

    @Override
    public RegistrationMetrics fetchRegistrationMetrics() {
        // TODO fetch these results via a JPA call and repackage the entities into UI Model records
        return new RegistrationMetrics(77, 115, 269, 460);
    }

    @Override
    public ScheduleMetrics fetchScheduleMetrics() {
        // TODO fetch these results via a JPA call and repackage the entities into UI Model records
        return new ScheduleMetrics(17);
    }

    @Override
    public TownHallMetrics fetchTownHallMetrics() {
        // TODO fetch these results via a JPA call and repackage the entities into UI Model records
        return new TownHallMetrics(
                List.of(
                        new TopicGroupedQuestions("Products", 8),
                        new TopicGroupedQuestions("Research", 3),
                        new TopicGroupedQuestions("Financials", 9),
                        new TopicGroupedQuestions("Investors", 1)
                ),
                List.of(
                        new TopicVotes("Products", 88),
                        new TopicVotes("Research", 57),
                        new TopicVotes("Financials", 21),
                        new TopicVotes("Investors", 3)
                ));
    }
}
