package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
@Entity
public class AlkoUser extends BaseEntity{
    @NotNull
    String name;

    @NotNull
    Gender gender;

    /**
     * height in cm
     */
    @NotNull
    int height;

    /**
     * weight in kg
     */
    @NotNull
    int weight;

    @NotNull
    int age;

    @NotNull
    Smoker smoker;
}
