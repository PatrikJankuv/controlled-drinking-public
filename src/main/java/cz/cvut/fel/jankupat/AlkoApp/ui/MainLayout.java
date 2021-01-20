package cz.cvut.fel.jankupat.AlkoApp.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.PlaneChart;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.Setting;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.dashboard.GenderDashboard;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.list.ProfileList;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.list.UsersList;

/**
 * The type Main layout.
 *
 * @author Patrik Jankuv
 * @created 11 /14/2020
 */
@CssImport("./styles/shared-styles.css")
@PageTitle("Kontrolovane piti")
public class MainLayout extends AppLayout {
    /**
     * Instantiates a new Main layout.
     */
    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Controlled drinking back office");
        logo.addClassName("logo");

        Checkbox checkbox = new Checkbox();
        checkbox.setLabel("Dark mode");
        checkbox.addValueChangeListener(click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList(); //

            if (themeList.contains(Lumo.DARK)) { //
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });

        Button logout = new Button("Log out", click -> {
            UI.getCurrent().getPage().executeJs("location.assign('logout')");
        });

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, checkbox, logout);

        header.expand(logo);
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);

        header.addClassName("header");

        addToNavbar(header);
    }


    private void createDrawer() {
        RouterLink listLink = new RouterLink("Profiles", ProfileList.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink,
                new RouterLink("Dashboard", GenderDashboard.class),
                new RouterLink("Users", UsersList.class),
                new RouterLink("Plans stats", PlaneChart.class),
                new RouterLink("Settings", Setting.class)
        ));
    }
}