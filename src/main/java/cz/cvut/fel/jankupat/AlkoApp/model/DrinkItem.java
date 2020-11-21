package cz.cvut.fel.jankupat.AlkoApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.DrinkTypeEnum;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Collection;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */

@Entity
public class DrinkItem extends BaseEntity implements IEntity{


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "DAY_ID")
    private Day day;

    private String name;

    private String drinkType;

    private double price;

    //ml
    private int amount;

    private int count;

    private double alcohol;

    private Boolean planned;

    private String description;

    private LocalTime dateTime;

    public Day getDay() {
        return day;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Boolean getPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalTime dateTime) {
        this.dateTime = dateTime;
    }

}
