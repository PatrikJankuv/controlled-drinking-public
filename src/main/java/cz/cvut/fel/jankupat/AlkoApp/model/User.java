package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.Gender;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.Smoker;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
@Entity(name = "ALKO_USER")
public class User extends BaseEntity{
    @NotNull
    private String name;

    @NotNull
    private Gender gender;

    /**
     * height in cm
     */
    @NotNull
    private int height;

    /**
     * weight in kg
     */
    @NotNull
    private int weight;

    @NotNull
    private int age;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Smoker smoker;

    //todo make sql request which search alko_user for account
    @NonNull
    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

}
