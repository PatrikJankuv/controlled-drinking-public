package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Entity
public class Feeling extends BaseEntity{

    @Column(name = "feeling")
    private FeelingeEnum feel;

    public Feeling(FeelingeEnum feel) {
        this.feel = feel;
    }

}
