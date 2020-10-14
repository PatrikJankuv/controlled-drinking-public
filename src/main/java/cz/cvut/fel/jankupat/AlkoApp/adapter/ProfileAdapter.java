package cz.cvut.fel.jankupat.AlkoApp.adapter;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.Gender;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.Smoker;

/**
 * @author Patrik Jankuv
 * @created 10/14/2020
 */
public class ProfileAdapter {

    private String name;

    private Gender gender;

    private int weight;

    private int height;

    private int age;

    private Smoker smoker;

    public ProfileAdapter(String name, Gender gender, int weight, int height, int age, Smoker smoker) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.smoker = smoker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Smoker getSmoker() {
        return smoker;
    }

    public void setSmoker(Smoker smoker) {
        this.smoker = smoker;
    }
}
