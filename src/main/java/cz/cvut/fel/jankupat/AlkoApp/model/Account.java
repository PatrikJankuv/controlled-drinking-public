package cz.cvut.fel.jankupat.AlkoApp.model;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
class Account {

    @NotNull
    String email;

    @NonNull
    String password;

    @NotNull
    Account_role role;
}
