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
    private String gender;

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
    private String smoker;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PROFILE_DAY",
            joinColumns = @JoinColumn(name = "PROFILE_ID"),
            inverseJoinColumns = @JoinColumn(name = "DAY_ID"))
    private Collection<Day> days;


    @OneToMany
    @JoinTable(name = "PROFILE_ACHIEVEMENT",
            joinColumns = @JoinColumn(name = "PROFILE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACHIEVEMENT_ID"))
    private Collection<Achievement> achievements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getSmoker() {
        return smoker;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public Collection<Day> getDays() {
        return days;
    }

    public void addDay(Day day){
        days.add(day);
    }

    public void setDays(Collection<Day> days) {
        this.days = days;
    }

    public Collection<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Collection<Achievement> achievements) {
        this.achievements = achievements;
    }

    public void addAchievement(Achievement achievement){
        achievements.add(achievement);
    }
}
