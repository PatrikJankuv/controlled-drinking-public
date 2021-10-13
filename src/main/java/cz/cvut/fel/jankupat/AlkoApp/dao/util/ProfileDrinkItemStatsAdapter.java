package cz.cvut.fel.jankupat.AlkoApp.dao.util;

/**
 * The type Profile drink item stats adapter.
 *
 * @author Patrik Jankuv
 * @created 12 /6/2020
 */
public class ProfileDrinkItemStatsAdapter {
    /**
     * The Count.
     */
    long count;
    /**
     * The Type.
     */
    String type;
    /**
     * The Planned.
     */
    boolean planned;

    /**
     * Instantiates a new Profile drink item stats adapter.
     *
     * @param count   the count
     * @param type    the type
     * @param planned the planned
     */
    public ProfileDrinkItemStatsAdapter(long count, String type, boolean planned) {
        this.count = count;
        this.type = type;
        this.planned = planned;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public long getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(long count) {
        this.count = count;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Is planned boolean.
     *
     * @return the boolean
     */
    public boolean isPlanned() {
        return planned;
    }

    /**
     * Sets planned.
     *
     * @param planned the planned
     */
    public void setPlanned(boolean planned) {
        this.planned = planned;
    }
}
