package cz.cvut.fel.jankupat.AlkoApp.dao.util;

import java.time.LocalDate;

/**
 * @author Patrik Jankuv
 * @created 11/30/2020
 */
public class DayStatsAdapterExtend extends DayStatsAdapter {
    private long age;
    private long weight;
    private long height;
    private long gender;
    private long smoker;

    public DayStatsAdapterExtend(LocalDate dateTime, boolean plan, long count, long age, long gender, long smoker, long weight, long height) {
        super(dateTime, plan, count);
    }

    public long getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
