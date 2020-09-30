package cz.cvut.fel.jankupat.AlkoApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
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

//    @ElementCollection(targetClass = FeelingsEnum.class)
//    @JoinTable(name = "feelings", joinColumns = @JoinColumn(name = "day_id"))
//    @Column(name = "feeling", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Collection<FeelingsEnum> feelings;

    @JsonManagedReference
    @OneToMany(mappedBy = "day")
    private Set<DrinkItem> items;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "day_reflection",
            joinColumns =
                    { @JoinColumn(name = "day_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "reflection_id", referencedColumnName = "id") })
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
//    public Collection<FeelingsEnum> getFeelings() {
//        return feelings;
//    }
//
//    public void setFeelings(Collection<FeelingsEnum> feelings) {
//        this.feelings = feelings;
//    }
}
