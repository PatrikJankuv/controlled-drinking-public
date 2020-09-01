package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.AchievementEnum;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.Gender;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.Smoker;


import javax.persistence.*;
import java.util.Collection;


/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
@Entity
public class Profile extends BaseEntity implements IEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * height in cm
     */
    @Column(nullable = false)
    private int height;

    /**
     * weight in kg
     */
    @Column(nullable = false)
    private int weight;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Smoker smoker;

    @OneToMany
    @JoinTable(name = "PROFILE_DAY",
            joinColumns = @JoinColumn(name = "PROFILE_ID"),
            inverseJoinColumns = @JoinColumn(name = "DAY_ID"))
    private Collection<Day> days;

    @ElementCollection(targetClass = FeelingsEnum.class)
    @JoinTable(name = "PROFILE_ACHIEVEMENT", joinColumns = @JoinColumn(name = "day_id"))
    @Column(name = "ACHIEVEMENT", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<AchievementEnum> achievements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Smoker getSmoker() {
        return smoker;
    }

    public void setSmoker(Smoker smoker) {
        this.smoker = smoker;
    }

    public Collection<Day> getDays() {
        return days;
    }

    public void setDays(Collection<Day> days) {
        this.days = days;
    }

    public Collection<AchievementEnum> getAchievements() {
        return achievements;
    }

    public void setAchievements(Collection<AchievementEnum> achievements) {
        this.achievements = achievements;
    }
}
