package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.*;
import java.util.List;

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
    private List<User> userList;

}
