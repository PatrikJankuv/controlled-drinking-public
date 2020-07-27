package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.*;
import java.util.Set;
/**
 * @author Patrik Jankuv
 * @created 7/27/2020
 */

@Entity
@Table(name = "app_user")
public class User {
    int id;

    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
