package com.vaadin.builderschallenge.views.dashboard;

import com.vaadin.builderschallenge.services.DashboardService;
import com.vaadin.builderschallenge.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class DashboardView extends Scroller {
    private static final Logger LOG = LoggerFactory.getLogger(DashboardView.class);

    private static final int RESPONSIVE_STEP_SIZE = 400;
    private static final FormLayout.ResponsiveStep[] RESPONSIVE_STEPS = {
            new FormLayout.ResponsiveStep("0", 1),
            new FormLayout.ResponsiveStep("%dpx".formatted(RESPONSIVE_STEP_SIZE), 2),
            new FormLayout.ResponsiveStep("%dpx".formatted(RESPONSIVE_STEP_SIZE * 2), 4)
    };

    private final transient DashboardService dashboardService;

    public DashboardView(DashboardService dashboardService) {
        this.dashboardService = dashboardService;

        var layout = new VerticalLayout();
        layout.setSizeFull();
        layout.add(createRegistrationSection());
        layout.add(createLodgingSection());
        layout.add(createDiningSection());
        layout.add(createTownHallSection());
        layout.add(createHackathonSection());
        layout.add(createScheduleSection());

        this.setContent(layout);
    }

    private Component createSection(String title, Component... tiles) {
        var message = dashboardService.sayHello();
        LOG.info("Hello from {}: {}", title, message);

        var titleBar = new H1(title);

        var tileLayout = new FormLayout();
        tileLayout.setResponsiveSteps(RESPONSIVE_STEPS);
        tileLayout.setWidthFull();
        tileLayout.add(tiles);

        var sectionLayout = new VerticalLayout();
        sectionLayout.add(titleBar);
        sectionLayout.add(tileLayout);

        return sectionLayout;
    }

    private Component createTile(String label, Component widget) {
        var labelSlot = new H2(label);

        var tile = new VerticalLayout();
        tile.addClassNames(LumoUtility.Border.ALL,
                LumoUtility.BorderRadius.LARGE,
                LumoUtility.BorderColor.CONTRAST_10);
        tile.add(labelSlot);
        tile.add(widget);

        return tile;
    }

    private Component createRegistrationSection() {
        var registrationTile = createTile("Registration", new Text("A dial showing number of employees registered vs. total in company"));
        var participationTile = createTile("Participation", new Text("A dial showing number of employee-days registered vs. number of employee-days possible (try to show party part of dial in a different color—or even each day's sum as a different color)"));

        return createSection("Registration", registrationTile, participationTile);
    }

    private Component createLodgingSection() {
        var occupancyTile = createTile("Occupancy", new Text("A set of dials showing number of room-nights reserved vs. blocked by night"));

        return createSection("Lodging", occupancyTile);
    }

    private Component createDiningSection() {
        var diningTile = createTile("Dining", new Text("A dial showing the number of meals reserved vs. number possible"));
        var preferencesTile = createTile("Preferences", new Text("A dial showing the number of diners with food preferences vs. total diners"));
        var restrictionsTile = createTile("Restrictions", new Text("A dial showing the number of diners with food restrictions vs. total diners"));

        return createSection("Dining", diningTile, preferencesTile, restrictionsTile);
    }

    private Component createTownHallSection() {
        var questionsTile = createTile("Questions", new Text("A pie chart showing the number of grouped questions by topic"));
        var popularityTile = createTile("Popularity", new Text("A pie chart showing the number of votes per grouped question by topic"));

        return createSection("TownHall", questionsTile, popularityTile);
    }

    private Component createHackathonSection() {
        var ideasTile = createTile("Ideas", new Text("A single number showing the number of hackathon ideas"));
        var popularityTile = createTile("Popularity", new Text("A single number showing the number of hackathon upvotes"));
        var registrantsTile = createTile("Registrants", new Text("A single number showing the number of hackathon registrants"));

        return createSection("Hackathon", ideasTile, popularityTile, registrantsTile);
    }

    private Component createScheduleSection() {
        var eventsTile = createTile("Events", new Text("A single number showing the number of scheduled calendar events"));

        return createSection("Schedule", eventsTile);
    }

}
