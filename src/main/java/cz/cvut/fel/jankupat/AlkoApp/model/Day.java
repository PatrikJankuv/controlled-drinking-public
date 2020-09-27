package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;


/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Entity
public class Day extends BaseEntity implements IEntity{

    private String name;

    private String description;

    private LocalDate dateTime;

//    private String stickWithTime;
//
//    private Double savedMoney;
//
//    private Double maxMile;

    @ElementCollection(targetClass = FeelingsEnum.class)
    @JoinTable(name = "feelings", joinColumns = @JoinColumn(name = "day_id"))
    @Column(name = "feeling", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<FeelingsEnum> feelings;

    @OneToMany(mappedBy = "day")
    private Collection<DrinkItem> items;

    public Collection<DrinkItem> getItems() {
        return items;
    }

    public void setItems(Collection<DrinkItem> items) {
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

//    public String getStickWithTime() {
//        return stickWithTime;
//    }
//
//    public void setStickWithTime(String stickWithTime) {
//        this.stickWithTime = stickWithTime;
//    }
//
//    public Double getSavedMoney() {
//        return savedMoney;
//    }
//
//    public void setSavedMoney(Double savedMoney) {
//        this.savedMoney = savedMoney;
//    }
//
//    public Double getMaxMile() {
//        return maxMile;
//    }
//
//    public void setMaxMile(Double maxMile) {
//        this.maxMile = maxMile;
//    }

    public Collection<FeelingsEnum> getFeelings() {
        return feelings;
    }

    public void setFeelings(Collection<FeelingsEnum> feelings) {
        this.feelings = feelings;
    }
}
