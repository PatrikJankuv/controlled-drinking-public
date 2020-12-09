package cz.cvut.fel.jankupat.AlkoApp.ui.view.list;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.service.DrinkItemService;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
import cz.cvut.fel.jankupat.AlkoApp.ui.view.ProfileDetails;

/**
 * The type Profile form.
 */
@SpringComponent
@UIScope
public class ProfileForm extends FormLayout {

    /**
     * The Name.
     */
    TextField name = new TextField("Name");
    /**
     * The Gender.
     */
    TextField gender = new TextField("Gender");
    /**
     * The Weight.
     */
    TextField weight = new TextField("Weight");
    /**
     * The Height.
     */
    TextField height = new TextField("Height");
    /**
     * The Age.
     */
    TextField age = new TextField("Age");
    /**
     * The Smoker.
     */
    TextField smoker = new TextField("Smoker");


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
    /**
     * The Profile details.
     */
    Button profileDetails = new Button("Profile calendar");

    private Profile profile;
    /**
     * The Profile service.
     */
    public ProfileService profileService;
    /**
     * The Drink item service.
     */
    public DrinkItemService drinkItemService;
    /**
     * The Binder.
     */
    Binder<Profile> binder = new Binder<>(Profile.class);

    /**
     * Instantiates a new Profile form.
     */
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
        configureProfileDetailButton();

        add(
                profileDetails,
                name,
                age,
                weight,
                height,
                gender,
                smoker,
                createButtonsLayout()
        );
    }


    /**
     * Sets contact.
     *
     * @param contact the contact
     */
    void setContact(Profile contact) {
        this.profile = contact;
        binder.readBean(contact);
    }

    /**
     * Configure profile detail button.
     */
    public void configureProfileDetailButton() {
        profileDetails.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);
        profileDetails.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(ProfileDetails.class, profile.getId());
        });
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

    /**
     * Edit customer.
     *
     * @param c the c
     */
    public final void editCustomer(Profile c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            profile = profileService.find(c.getId());
        } else {
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

    /**
     * The type Contact form event.
     */
// Events
    public static abstract class ContactFormEvent extends ComponentEvent<ProfileForm> {
        private Profile contact;

        /**
         * Instantiates a new Contact form event.
         *
         * @param source  the source
         * @param contact the contact
         */
        protected ContactFormEvent(ProfileForm source, Profile contact) {
            super(source, false);
            this.contact = contact;
        }

        /**
         * Gets contact.
         *
         * @return the contact
         */
        public Profile getContact() {
            return contact;
        }
    }

    /**
     * The type Save event.
     */
    public static class SaveEvent extends ContactFormEvent {
        /**
         * Instantiates a new Save event.
         *
         * @param source  the source
         * @param contact the contact
         */
        SaveEvent(ProfileForm source, Profile contact) {
            super(source, contact);
        }
    }

    /**
     * The type Delete event.
     */
    public static class DeleteEvent extends ContactFormEvent {
        /**
         * Instantiates a new Delete event.
         *
         * @param source  the source
         * @param contact the contact
         */
        DeleteEvent(ProfileForm source, Profile contact) {
            super(source, contact);
        }

    }

    /**
     * The type Close event.
     */
    public static class CloseEvent extends ContactFormEvent {
        /**
         * Instantiates a new Close event.
         *
         * @param source the source
         */
        CloseEvent(ProfileForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}



