package cz.cvut.fel.jankupat.AlkoApp.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.cvut.fel.jankupat.AlkoApp.model.User;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;

/**
 * @author Patrik Jankuv
 * @created 1/6/2021
 */
@Route(value = "settings", layout = MainLayout.class)
@PageTitle("Settings")
public class Setting extends VerticalLayout {
    private PasswordField oldPasswordField;
    private PasswordField passwordField2;
    private Label info;

    public Setting() {
        add(new H3("Settings"));
        changePasswordForm();
    }

    private void changePasswordForm() {
        VerticalLayout changePasswordForm = new VerticalLayout();
        oldPasswordField = new PasswordField();
        passwordField2 = new PasswordField();
        info = new Label();
        info.setVisible(false);

        oldPasswordField.setLabel("Current password");
        oldPasswordField.setPlaceholder("Enter password");
        oldPasswordField.setRevealButtonVisible(false);

        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("New password");
        passwordField.setPlaceholder("Enter password");


        passwordField2.setLabel("New password repeat");
        passwordField2.setPlaceholder("Enter password");

        Button changePassword = new Button("Change password");
        changePassword.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        Binder<User> binder = new Binder<>();

        // Store return date binding so we can revalidate it later
        Binder.BindingBuilder<User, String> returnBindingBuilder = binder
                .forField(passwordField2).withValidator(
                        returnDate -> returnDate.equals(passwordField.getValue()),
                        "Passwords don't match");
        Binder.Binding<User, String> returnBinder = returnBindingBuilder
                .bind(User::getPassword, User::setPassword);

        // Revalidate return date when departure date changes
        passwordField2.addValueChangeListener(event -> returnBinder.validate());
        passwordField2.setRevealButtonVisible(false);

        changePassword.addClickListener(buttonClickEvent -> {
            if (!passwordField.getValue().equals(passwordField2.getValue())) {
//                info.setText("Password don't match");
//                info.setVisible(true);
            } else {
//                info.setVisible(false);
            }
        });

        changePasswordForm.add(oldPasswordField, passwordField, passwordField2, changePassword, info);

        add(changePasswordForm);
    }
}
