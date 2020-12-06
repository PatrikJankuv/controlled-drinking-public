package cz.cvut.fel.jankupat.AlkoApp.adapter;

/**
 * The type Profile adapter.
 *
 * @author Patrik Jankuv
 * @created 10 /14/2020
 */
public class ProfileAdapter {

    private String name;

    private String gender;

    private int weight;

    private int height;

    private int age;

    private String smoker;

    /**
     * Instantiates a new Profile adapter.
     *
     * @param name   the name
     * @param gender the gender
     * @param weight the weight
     * @param height the height
     * @param age    the age
     * @param smoker the smoker
     */
    public ProfileAdapter(String name, String gender, int weight, int height, int age, String smoker) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.smoker = smoker;
    }

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
     * Gets gender.
     *
     * @return the gender
     */
    public String  getGender() {
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
}
