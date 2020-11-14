package cz.cvut.fel.jankupat.AlkoApp.vaadin;

/**
 * @author Patrik Jankuv
 * @created 11/13/2020
 */
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.repository.ProfileRepository;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A simple example to introduce building forms. As your real application is probably much
 * more complicated than this example, you could re-use this form in multiple places. This
 * example component is only used in MainView.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX.
 */
@SpringComponent
@UIScope
public class ProfileEditor extends VerticalLayout implements KeyNotifier {

    private final ProfileService service;

    /**
     * The currently edited customer
     */
    private Profile customer;

    /* Fields to edit properties in Customer entity */
    TextField name = new TextField("Name");
    TextField gender = new TextField("Gender");
    TextField weight = new TextField("Weight");
    TextField height = new TextField("Height");
    TextField age = new TextField("Age");
    TextField smoker = new TextField("Smoker");

    /* Action buttons */
    // TODO why more code?
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Profile> binder = new Binder<>(Profile.class);
    private ChangeHandler changeHandler;

    @Autowired
    public ProfileEditor(ProfileRepository repository, ProfileService service) {

        this.service = service;
        HorizontalLayout items = new HorizontalLayout(name, gender, weight, height, age, smoker);
        add(items, actions);

        // bind using naming convention

        binder.forField(weight)
                .withConverter(
                        new StringToIntegerConverter("Must enter a number"))
                .bind(Profile::getWeight, Profile::setWeight);
        binder.forField(height)
                .withConverter(
                        new StringToIntegerConverter("Must enter a number"))
                .bind(Profile::getHeight, Profile::setHeight);
        binder.forField(age)
                .withConverter(
                        new StringToIntegerConverter("Must enter a number"))
                .bind(Profile::getAge, Profile::setAge);
        binder.bindInstanceFields(this);
        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editUser(customer));
        setVisible(false);
    }

    void delete() {
        service.remove(customer);
        changeHandler.onChange();
    }

    void save() {
        service.update(customer);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editUser(Profile c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            customer = service.find(c.getId());
        }
        else {
            customer = c;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(customer);

        setVisible(true);

        // Focus first name initially
        name.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}