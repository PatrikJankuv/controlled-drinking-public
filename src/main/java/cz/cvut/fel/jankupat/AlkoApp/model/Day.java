package cz.cvut.fel.jankupat.AlkoApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


/**
 * @author Patrik Jankuv
 * @created 8/3/2020
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


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reflection_id")
    private Reflection reflection;


    public Set<DrinkItem> getItems() {
        return items;
    }

    public void setItems(Set<DrinkItem> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public Reflection getReflection() {
        return reflection;
    }

    public void setReflection(Reflection reflection) {
        this.reflection = reflection;
    }
}
