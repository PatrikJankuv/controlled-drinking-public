package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.Gender;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.Smoker;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
@Entity(name = "ALKO_USER")
public class User extends BaseEntity{
    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * height in cm
     */
    @NotNull
    private int height;

    /**
     * weight in kg
     */
    @NotNull
    private int weight;

    @NotNull
    private int age;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Smoker smoker;

    //todo make sql request which search alko_user for account
    @NonNull
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
