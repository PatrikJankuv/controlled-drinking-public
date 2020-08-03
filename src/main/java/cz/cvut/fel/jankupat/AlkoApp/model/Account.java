package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.Account_role;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
@Entity
public class Account extends BaseEntity{

    @NotNull
    String email;

    @NonNull
    String password;

    @NotNull
    Account_role role;
}
