package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    private List<Feeling> feelings;

}
