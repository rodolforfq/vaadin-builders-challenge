package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.builderschallenge.uimodel.TownHallMetrics;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TownHallSection extends Section {
    private static final Logger LOG = LoggerFactory.getLogger(TownHallSection.class);

    private final transient TownHallMetrics townHallMetrics;

    public TownHallSection(DashboardService dashboardService) {
        super("Town Hall");

        townHallMetrics = dashboardService.fetchTownHallMetrics();
        LOG.info("TownHallMetrics: {}", townHallMetrics);

        addTile(createQuestionsWidget());
        addTile(createPopularityWidget());
    }

    private Component createQuestionsWidget() {
        return ChartFactory.createPie("Questions",
                "The number of grouped questions by topic",
                townHallMetrics.topicGroupedQuestionsList().stream()
                        .map(topicGroupedQuestions ->
                                new ChartFactory.PieSlice(topicGroupedQuestions.topic(),
                                        topicGroupedQuestions.groupedQuestionCount()))
                        .toArray(ChartFactory.PieSlice[]::new));
    }

    private Component createPopularityWidget() {
        return ChartFactory.createPie("Popularity",
                "The number of votes per grouped question by topic",
                townHallMetrics.topicVotesList().stream()
                        .map(topicGroupedQuestions ->
                                new ChartFactory.PieSlice(topicGroupedQuestions.topic(),
                                        topicGroupedQuestions.voteCount()))
                        .toArray(ChartFactory.PieSlice[]::new));
    }
}
