package cz.cvut.fel.jankupat.AlkoApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

/**
 * The type Drink item.
 *
 * @author Patrik Jankuv
 * @created 8 /3/2020
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

    /**
     * Gets day.
     *
     * @return the day
     */
    public Day getDay() {
        return day;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return description;
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     */
    public void setDesc(String desc) {
        this.description = desc;
    }

    /**
     * Sets day.
     *
     * @param day the day
     */
    public void setDay(Day day) {
        this.day = day;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets drink type.
     *
     * @return the drink type
     */
    public String getDrinkType() {
        return drinkType;
    }

    /**
     * Sets drink type.
     *
     * @param drinkType the drink type
     */
    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Gets planned.
     *
     * @return the planned
     */
    public Boolean getPlanned() {
        return planned;
    }

    /**
     * Sets planned.
     *
     * @param planned the planned
     */
    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets alcohol.
     *
     * @return the alcohol
     */
    public double getAlcohol() {
        return alcohol;
    }

    /**
     * Sets alcohol.
     *
     * @param alcohol the alcohol
     */
    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets date time.
     *
     * @return the date time
     */
    public LocalTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets date time.
     *
     * @param dateTime the date time
     */
    public void setDateTime(LocalTime dateTime) {
        this.dateTime = dateTime;
    }

}
