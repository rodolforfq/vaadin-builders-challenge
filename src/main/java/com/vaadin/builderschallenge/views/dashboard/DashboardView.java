package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.builderschallenge.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class DashboardView extends Composite<Scroller> {
//    private static final Logger LOG = LoggerFactory.getLogger(DashboardView.class);
    private static final int RESPONSIVE_STEP_SIZE = 400;
    private static final ResponsiveStep[] RESPONSIVE_STEPS = {
            new ResponsiveStep("0", 1),
            new ResponsiveStep("%dpx".formatted(RESPONSIVE_STEP_SIZE), 2),
            new ResponsiveStep("%dpx".formatted(RESPONSIVE_STEP_SIZE * 2), 4)
    };

    public static ResponsiveStep[] getResponsiveSteps() {
        return RESPONSIVE_STEPS;
    }

    public DashboardView(DashboardService dashboardService) {
        var layout = new VerticalLayout();
        layout.setSizeFull();
        layout.add(new RegistrationSection(dashboardService));
        layout.add(new LodgingSection(dashboardService));
        layout.add(new DiningSection(dashboardService));
        layout.add(new TownHallSection(dashboardService));
        layout.add(new HackathonSection(dashboardService));
        layout.add(new ScheduleSection(dashboardService));

        var content = getContent();
        content.setContent(layout);
    }
}
