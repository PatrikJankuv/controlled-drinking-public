package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.DrinkTypeEnum;

import javax.persistence.*;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Entity
public class DrinkItem extends BaseEntity implements IEntity{

    @ManyToOne
    @JoinColumn(name = "DAY_ID")
    private Day day;

    private String name;

    private DrinkTypeEnum drinkType;

    private int price;

    private int amount = 0;

    private int alcohol;

    private String description;

    private Boolean planned = false;
}
