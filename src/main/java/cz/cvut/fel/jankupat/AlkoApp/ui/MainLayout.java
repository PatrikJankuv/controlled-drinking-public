package cz.cvut.fel.jankupat.AlkoApp.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.CalendarView;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.GenderDashboard;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.PlaneChart;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.list.ProfileList;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.list.UsersList;

/**
 * @author Patrik Jankuv
 * @created 11/14/2020
 */
@CssImport("./styles/shared-styles.css")
@PageTitle("Kontrolovane piti")
public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Kontrolované pití");
        logo.addClassName("logo");
        Anchor logout = new Anchor("logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.expand(logo);
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);

        header.addClassName("header");

        addToNavbar(header);
    }


    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ProfileList.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink,
                new RouterLink("Dashboard", GenderDashboard.class),
//                new RouterLink("Stats", CalendarView.class, 1),
                new RouterLink("Users", UsersList.class),
                new RouterLink("Stats", PlaneChart.class)
        ));
    }
}