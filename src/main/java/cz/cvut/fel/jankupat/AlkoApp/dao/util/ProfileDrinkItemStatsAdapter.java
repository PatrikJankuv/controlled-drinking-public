package cz.cvut.fel.jankupat.AlkoApp.dao.util;

/**
 * @author Patrik Jankuv
 * @created 12/6/2020
 */
public class ProfileDrinkItemStatsAdapter {
    long count;
    String type;
    boolean planned;

    public ProfileDrinkItemStatsAdapter(long count, String type, boolean planned) {
        this.count = count;
        this.type = type;
        this.planned = planned;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPlanned() {
        return planned;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }
}
