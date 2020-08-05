package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.Gender;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.Smoker;


import javax.persistence.*;


/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
@Entity(name = "ALKO_USER")
public class User extends BaseEntity implements IEntity{
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

    //todo make sql request which search alko_user for account
    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
