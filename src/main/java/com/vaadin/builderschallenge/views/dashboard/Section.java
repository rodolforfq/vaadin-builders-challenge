package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Section extends Composite<VerticalLayout> {

    private final FormLayout tileLayout;
    private final String title;

    public Section(String title) {
        this.title = title;

        var titleBar = new H1(title);

        tileLayout = new FormLayout();
        tileLayout.setResponsiveSteps(DashboardView.getResponsiveSteps());
        tileLayout.setWidthFull();

        var content = getContent();
        content.setPadding(false);
        content.add(titleBar);
        content.add(tileLayout);
    }

    public String getTitle() {
        return title;
    }

    public Tile addTile(String label, Component widget) {
        var tile = new Tile(label, widget);
        tileLayout.add(tile);
        return tile;
    }
}
