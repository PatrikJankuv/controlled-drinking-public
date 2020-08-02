package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
@Entity(name = "USER_ACHIEVEMENT")
public class Achievement extends BaseEntity {


    @OneToMany
    @JoinColumn(name = "alko_user_id")
    private AlkoUser alkoUser;

    private Boolean isDone = false;

}
