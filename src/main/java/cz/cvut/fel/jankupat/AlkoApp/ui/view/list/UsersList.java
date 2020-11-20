package cz.cvut.fel.jankupat.AlkoApp.ui.view.list;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.User;
import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;

/**
 * @author Patrik Jankuv
 * @created 11/17/2020
 */
@Route(value = "user", layout = MainLayout.class)
public class UsersList extends VerticalLayout {
    Grid<User> grid = new Grid<>(User.class);
    private final UserForm form;
    private UserRepository repository;

    public UsersList(UserRepository repository){

        this.repository = repository;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        this.form = new UserForm();
        form.addListener(UserForm.SaveEvent.class, this::saveContact);
        form.addListener(UserForm.DeleteEvent.class, this::deleteContact);
        form.addListener(UserForm.CloseEvent.class, e -> closeEditor());

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();
        add(content);

        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("id", "email", "name", "provider");


        grid.asSingleSelect().addValueChangeListener(event ->
                editUser(event.getValue()));
    }

    private void updateList() {
        grid.setItems(repository.findAll());
    }
    public void editUser(User user) {
        if (user == null) {
            closeEditor();
        } else {
            form.setUser(user);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setUser(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    public void editContact(User contact) {
        if (contact == null) {
            closeEditor();
        } else {
            form.setUser(contact);
            form.setVisible(true);
            addClassName("editing");
        }
    }


    private void saveContact(UserForm.SaveEvent event) {
        repository.save(event.getUser());
        updateList();
        closeEditor();
    }

    private void deleteContact(UserForm.DeleteEvent event) {
        repository.delete(event.getUser());
        updateList();
        closeEditor();
    }
}
