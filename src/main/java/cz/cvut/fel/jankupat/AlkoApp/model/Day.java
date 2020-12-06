package cz.cvut.fel.jankupat.AlkoApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DrinkItem> items;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reflection_id")
    private Reflection reflection;

    private Boolean planAccomplished;

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
