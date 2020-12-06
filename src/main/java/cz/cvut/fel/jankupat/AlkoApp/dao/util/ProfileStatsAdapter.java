package cz.cvut.fel.jankupat.AlkoApp.dao.util;

/**
 * The type Profile stats adapter.
 *
 * @author Patrik Jankuv
 * @created 12 /2/2020
 */
public class ProfileStatsAdapter {
    /**
     * The Avg age.
     */
    double avgAge;
    /**
     * The Avg weigh.
     */
    double avgWeigh;
    /**
     * The Avg height.
     */
    double avgHeight;
    /**
     * The Gender.
     */
    String gender;

    /**
     * Instantiates a new Profile stats adapter.
     *
     * @param avgAge    the avg age
     * @param avgWeigh  the avg weigh
     * @param avgHeight the avg height
     * @param gender    the gender
     */
    public ProfileStatsAdapter(double avgAge, double avgWeigh, double avgHeight, String gender) {
        this.avgAge = avgAge;
        this.avgWeigh = avgWeigh;
        this.avgHeight = avgHeight;
        this.gender = gender;
    }

    /**
     * Gets avg age.
     *
     * @return the avg age
     */
    public double getAvgAge() {
        return avgAge;
    }

    /**
     * Sets avg age.
     *
     * @param avgAge the avg age
     */
    public void setAvgAge(long avgAge) {
        this.avgAge = avgAge;
    }

    /**
     * Gets avg weigh.
     *
     * @return the avg weigh
     */
    public double getAvgWeigh() {
        return avgWeigh;
    }

    /**
     * Sets avg weigh.
     *
     * @param avgWeigh the avg weigh
     */
    public void setAvgWeigh(long avgWeigh) {
        this.avgWeigh = avgWeigh;
    }

    /**
     * Gets avg height.
     *
     * @return the avg height
     */
    public double getAvgHeight() {
        return avgHeight;
    }

    /**
     * Sets avg height.
     *
     * @param avgHeight the avg height
     */
    public void setAvgHeight(long avgHeight) {
        this.avgHeight = avgHeight;
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
}
