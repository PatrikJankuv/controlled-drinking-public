package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.*;
import java.util.Collection;


/**
 * The type Profile.
 *
 * @author Patrik Jankuv
 * @created 8 /2/2020
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
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets smoker.
     *
     * @return the smoker
     */
    public String getSmoker() {
        return smoker;
    }

    /**
     * Sets smoker.
     *
     * @param smoker the smoker
     */
    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    /**
     * Gets days.
     *
     * @return the days
     */
    public Collection<Day> getDays() {
        return days;
    }

    /**
     * Add day.
     *
     * @param day the day
     */
    public void addDay(Day day){
        days.add(day);
    }

    /**
     * Sets days.
     *
     * @param days the days
     */
    public void setDays(Collection<Day> days) {
        this.days = days;
    }

    /**
     * Gets achievements.
     *
     * @return the achievements
     */
    public Collection<Achievement> getAchievements() {
        return achievements;
    }

    /**
     * Sets achievements.
     *
     * @param achievements the achievements
     */
    public void setAchievements(Collection<Achievement> achievements) {
        this.achievements = achievements;
    }

    /**
     * Add achievement.
     *
     * @param achievement the achievement
     */
    public void addAchievement(Achievement achievement){
        achievements.add(achievement);
    }
}
