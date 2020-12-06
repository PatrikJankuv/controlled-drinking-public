package cz.cvut.fel.jankupat.AlkoApp.dao.util;

import java.time.LocalDate;

/**
 * The type Day stats adapter extend.
 *
 * @author Patrik Jankuv
 * @created 11 /30/2020
 */
public class DayStatsAdapterExtend extends DayStatsAdapter {
    private long age;
    private long weight;
    private long height;
    private long gender;
    private long smoker;

    /**
     * Instantiates a new Day stats adapter extend.
     *
     * @param dateTime the date time
     * @param plan     the plan
     * @param count    the count
     * @param age      the age
     * @param gender   the gender
     * @param smoker   the smoker
     * @param weight   the weight
     * @param height   the height
     */
    public DayStatsAdapterExtend(LocalDate dateTime, boolean plan, long count, long age, long gender, long smoker, long weight, long height) {
        super(dateTime, plan, count);
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public long getAge() {
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
     * Gets weight.
     *
     * @return the weight
     */
    public long getWeight() {
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
     * Gets height.
     *
     * @return the height
     */
    public long getHeight() {
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
}
