package cz.cvut.fel.jankupat.AlkoApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


/**
 * The type Day.
 *
 * @author Patrik Jankuv
 * @created 8 /3/2020
 */
@Entity
@Table(name="DAY")
public class Day extends BaseEntity implements IEntity{

    private String name;

    private String description;

    private LocalDate dateTime;

    private Double planPerMile;

    private Integer planMoney;

    private Integer planAlcoholVolume;

    @JsonManagedReference
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<DrinkItem> items;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reflection_id")
    private Reflection reflection;

    @Column(columnDefinition = "boolean default true")
    private Boolean planAccomplished;

    /**
     * Gets plan per mile.
     *
     * @return the plan per mile
     */
    public Double getPlanPerMile() {
        return planPerMile;
    }

    /**
     * Sets plan per mile.
     *
     * @param planPerMile the plan per mile
     */
    public void setPlanPerMile(Double planPerMile) {
        this.planPerMile = planPerMile;
    }

    /**
     * Gets plan money.
     *
     * @return the plan money
     */
    public Integer getPlanMoney() {
        return planMoney;
    }

    /**
     * Sets plan money.
     *
     * @param planMoney the plan money
     */
    public void setPlanMoney(Integer planMoney) {
        this.planMoney = planMoney;
    }

    /**
     * Gets plan alcohol volume.
     *
     * @return the plan alcohol volume
     */
    public Integer getPlanAlcoholVolume() {
        return planAlcoholVolume;
    }

    /**
     * Sets plan alcohol volume.
     *
     * @param planAlcoholVolume the plan alcohol volume
     */
    public void setPlanAlcoholVolume(Integer planAlcoholVolume) {
        this.planAlcoholVolume = planAlcoholVolume;
    }


    /**
     * Gets plan accomplished.
     *
     * @return the plan accomplished
     */
    public Boolean getPlanAccomplished() {
        return planAccomplished;
    }

    /**
     * Sets plan accomplished.
     *
     * @param planAccomplished the plan accomplished
     */
    public void setPlanAccomplished(Boolean planAccomplished) {
        this.planAccomplished = planAccomplished;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public Set<DrinkItem> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(Set<DrinkItem> items) {
        this.items = items;
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
    public LocalDate getDateTime() {
        return dateTime;
    }

    /**
     * Sets date time.
     *
     * @param dateTime the date time
     */
    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets reflection.
     *
     * @return the reflection
     */
    public Reflection getReflection() {
        return reflection;
    }

    /**
     * Sets reflection.
     *
     * @param reflection the reflection
     */
    public void setReflection(Reflection reflection) {
        this.reflection = reflection;
    }
}
