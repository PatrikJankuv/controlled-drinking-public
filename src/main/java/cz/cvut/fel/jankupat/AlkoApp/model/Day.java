package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Entity
public class Day extends BaseEntity {

    @ManyToOne
    private AlkoUser alkoUser;

    private String name;

    private String description;

    private LocalDate dateTime;

    private String stickWithTime;

    private Double savedMoney;

    private Double maxMile;

}
