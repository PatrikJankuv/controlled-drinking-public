package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Entity
public class Feeling extends BaseEntity{

    @Column(name = "feeling")
    private FeelingsEnum feel;

//    public Feeling(FeelingsEnum feel) {
//        this.feel = feel;
//    }

}
