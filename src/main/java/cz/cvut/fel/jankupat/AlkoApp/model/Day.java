package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Entity
public class Day extends BaseEntity {

    @ManyToOne
    private User user;

    private String name;

    private String description;

    private LocalDate dateTime;

    private String stickWithTime;

    private Double savedMoney;

    private Double maxMile;

    //todo try to make it only from enums
    @ManyToMany
    private List<Feeling> feelings;

}
