package com.vaadin.builderschallenge.uimodel;

public record DiningMetrics(
        int mealsReservedCount,
        int mealsPossibleCount,
        int foodPreferenceCount,
        int foodRestrictionCount,
        int dinerCount
) {
}
