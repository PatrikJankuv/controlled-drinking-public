package cz.cvut.fel.jankupat.AlkoApp.ui.view.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.shared.Registration;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;

@SpringComponent
@UIScope
public class ProfileForm extends FormLayout {

    TextField name = new TextField("Name");
    TextField gender = new TextField("Gender");
    TextField weight = new TextField("Weight");
    TextField height = new TextField("Height");
    TextField age = new TextField("Age");
    TextField smoker = new TextField("Smoker");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    private Profile profile;
    ProfileService service;
    Binder<Profile> binder = new Binder<>(Profile.class);

    public ProfileForm() {
        addClassName("contact-form");
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


        add(
                name,
                age,
                weight,
                height,
                gender,
                smoker,
                createButtonsLayout()
        );
    }

    public void setContact(Profile contact) {
        this.profile = contact;
        binder.readBean(contact);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, profile)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public final void editCustomer(Profile c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            profile = service.find(c.getId());
        }
        else {
            profile = c;
        }

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(profile);

        setVisible(true);

        // Focus first name initially
        name.focus();
    }

    private void validateAndSave() {

        try {
            binder.writeBean(profile);
            fireEvent(new SaveEvent(this, profile));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
    // Events
    public static abstract class ContactFormEvent extends ComponentEvent<ProfileForm> {
        private Profile contact;

        protected ContactFormEvent(ProfileForm source, Profile contact) {
            super(source, false);
            this.contact = contact;
        }

        public Profile getContact() {
            return contact;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(ProfileForm source, Profile contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(ProfileForm source, Profile contact) {
            super(source, contact);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(ProfileForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}



