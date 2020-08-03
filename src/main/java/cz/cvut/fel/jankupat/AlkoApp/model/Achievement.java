package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
@Entity(name = "ACHIEVEMENT")
public class Achievement extends BaseEntity {

    private String name;

    private int Image;

    private String description;

    /**
     * list of users, which gained achievement
     */
    @OneToMany
    private List<AlkoUser> alkoUserList;

}
