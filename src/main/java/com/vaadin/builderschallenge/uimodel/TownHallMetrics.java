package com.vaadin.builderschallenge.uimodel;

import java.util.List;

public record TownHallMetrics(
        List<TopicGroupedQuestions> topicGroupedQuestionsList,
        List<TopicVotes> topicVotesList
) {
    public record TopicGroupedQuestions(
            String topic,
            int groupedQuestionCount
    ) {
    }
    public record TopicVotes(
            String topic,
            int voteCount
    ) {
    }
}
