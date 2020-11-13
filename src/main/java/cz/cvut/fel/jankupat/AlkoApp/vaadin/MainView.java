package cz.cvut.fel.jankupat.AlkoApp.vaadin;

/**
 * @author Patrik Jankuv
 * @created 11/13/2020
 */
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.User;
import cz.cvut.fel.jankupat.AlkoApp.repository.ProfileRepository;
import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;
import org.springframework.util.StringUtils;

@Route
public class MainView extends VerticalLayout {

    private final ProfileRepository repo;

    private final ProfileEditor editor;

    final Grid<Profile> grid;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(ProfileRepository repo, ProfileEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Profile.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "name", "gender", "weight", "height", "age", "smoker");
        grid.getColumnByKey("id").setWidth("100px").setFlexGrow(0);

        filter.setPlaceholder("Filter by name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editUser(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editUser(new Profile()));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });

        // Initialize listing
        listCustomers(null);
    }

    // tag::listCustomers[]
    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findByNameStartsWithIgnoreCase(filterText));
        }
    }
    // end::listCustomers[]

}
