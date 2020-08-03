package cz.cvut.fel.jankupat.AlkoApp.model;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
@Entity
class Account extends BaseEntity{

    @NotNull
    String email;

    @NonNull
    String password;

    @NotNull
    Account_role role;
}
