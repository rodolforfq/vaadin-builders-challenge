package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class Tile extends Composite<VerticalLayout> {

    public Tile(String label, Component widget) {
        var labelSlot = new H2(label);

        var content = getContent();
        content.addClassNames(LumoUtility.Border.ALL,
                LumoUtility.BorderRadius.LARGE,
                LumoUtility.BorderColor.CONTRAST_10);
        content.add(labelSlot);
        content.add(widget);
    }
}
