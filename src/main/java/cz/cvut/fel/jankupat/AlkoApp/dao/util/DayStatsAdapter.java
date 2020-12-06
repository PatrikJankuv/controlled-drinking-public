package cz.cvut.fel.jankupat.AlkoApp.dao.util;

import java.time.LocalDate;

/**
 * The type Day stats adapter.
 *
 * @author Patrik Jankuv
 * @created 11 /21/2020
 */
public class DayStatsAdapter {
    private LocalDate dateTime;
    private boolean plan;
    private long count;

    /**
     * Instantiates a new Day stats adapter.
     *
     * @param dateTime the date time
     * @param plan     the plan
     * @param count    the count
     */
    public DayStatsAdapter(LocalDate dateTime, boolean plan, long count) {
        this.dateTime = dateTime;
        this.plan = plan;
        this.count = count;
    }

    /**
     * Gets date time.
     *
     * @return the date time
     */
    public LocalDate getDateTime() {
        return dateTime;
    }

    /**
     * Is plan boolean.
     *
     * @return the boolean
     */
    public boolean isPlan() {
        return plan;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public long getCount() {
        return count;
    }
}
