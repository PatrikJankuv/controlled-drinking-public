package cz.cvut.fel.jankupat.AlkoApp.ui.view.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import cz.cvut.fel.jankupat.AlkoApp.model.User;
import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;

/**
 * The type User form.
 *
 * @author Patrik Jankuv
 * @created 11 /17/2020
 */
@SpringComponent
@UIScope
public class UserForm extends FormLayout {
    /**
     * The Email.
     */
    TextField email = new TextField("Email");
    /**
     * The Name.
     */
    TextField name = new TextField("Name");
//    TextField provider = new TextField("Provider");
//    TextField profile = new TextField("Profile id");


    /**
     * The Save.
     */
    Button save = new Button("Save");
    /**
     * The Delete.
     */
    Button delete = new Button("Delete");
    /**
     * The Close.
     */
    Button close = new Button("Cancel");

    private User user;
    private UserRepository repository;
    private Binder<User> binder = new Binder<>(User.class);

    /**
     * Instantiates a new User form.
     */
    UserForm(){
        addClassName("contact-form");
        binder.bindInstanceFields(this);

        add(email, name, createButtonsLayout());
    }

    /**
     * Set user.
     *
     * @param user the user
     */
    public void setUser(User user){
        this.user = user;
        binder.readBean(user);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new UserForm.DeleteEvent(this, user)));
        close.addClickListener(click -> fireEvent(new UserForm.CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(user);
            fireEvent(new UserForm.SaveEvent(this, user));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    /**
     * The type User form event.
     */
    public static abstract class UserFormEvent extends ComponentEvent<UserForm> {
        private User contact;

        /**
         * Instantiates a new User form event.
         *
         * @param source  the source
         * @param contact the contact
         */
        protected UserFormEvent(UserForm source, User contact) {
            super(source, false);
            this.contact = contact;
        }

        /**
         * Gets user.
         *
         * @return the user
         */
        public User getUser() {
            return contact;
        }
    }

    /**
     * The type Save event.
     */
    public static class SaveEvent extends UserFormEvent {
        /**
         * Instantiates a new Save event.
         *
         * @param source  the source
         * @param contact the contact
         */
        SaveEvent(UserForm source, User contact) {
            super(source, contact);
        }
    }

    /**
     * The type Delete event.
     */
    public static class DeleteEvent extends UserFormEvent {
        /**
         * Instantiates a new Delete event.
         *
         * @param source  the source
         * @param contact the contact
         */
        DeleteEvent(UserForm source, User contact) {
            super(source, contact);
        }

    }

    /**
     * The type Close event.
     */
    public static class CloseEvent extends UserFormEvent {
        /**
         * Instantiates a new Close event.
         *
         * @param source the source
         */
        CloseEvent(UserForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
