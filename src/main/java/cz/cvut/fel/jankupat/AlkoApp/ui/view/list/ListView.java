package cz.cvut.fel.jankupat.AlkoApp.ui.view.list;

/**
 * @author Patrik Jankuv
 * @created 11/13/2020
 */

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.repository.ProfileRepository;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.GenderDashboard;


@Route(value = "", layout = MainLayout.class)
public class ListView extends VerticalLayout {

    private final ProfileForm form;
    Grid<Profile> grid = new Grid<>(Profile.class);
    TextField filterText = new TextField();

    private ProfileService contactService;
    private ProfileRepository profileRepository;

    public ListView(ProfileService contactService, ProfileRepository repository) {
        this.contactService = contactService;
        this.profileRepository = repository;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new ProfileForm();
        form.addListener(ProfileForm.SaveEvent.class, this::saveContact);
        form.addListener(ProfileForm.DeleteEvent.class, this::deleteContact);
        form.addListener(ProfileForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();

        closeEditor();

    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("id", "name", "age", "weight", "height", "gender", "smoker");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editContact(event.getValue()));
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Pridaj profil");
        addContactButton.addClickListener(click -> addContact());

        Button statistic = new Button("Štatistika");
        statistic.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        RouterLink routerLink = new RouterLink("", GenderDashboard.class);
        routerLink.getElement().appendChild(statistic.getElement());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton, routerLink);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Profile());
    }

    private void updateList() {
        grid.setItems(profileRepository.findByNameStartsWithIgnoreCase(filterText.getValue()));
    }

    public void editContact(Profile contact) {
        if (contact == null) {
            closeEditor();
        } else {
            form.setContact(contact);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void saveContact(ProfileForm.SaveEvent event) {
        contactService.update(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(ProfileForm.DeleteEvent event) {
        contactService.remove(event.getContact());
        updateList();
        closeEditor();
    }
}
