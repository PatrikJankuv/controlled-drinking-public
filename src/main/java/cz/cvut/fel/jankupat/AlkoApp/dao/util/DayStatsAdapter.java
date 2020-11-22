package cz.cvut.fel.jankupat.AlkoApp.dao.util;

import java.time.LocalDate;

/**
 * @author Patrik Jankuv
 * @created 11/21/2020
 */
public class DayStatsAdapter {
    private LocalDate dateTime;
    private boolean plan;
    private long count;

    public DayStatsAdapter(LocalDate dateTime, boolean plan, long count) {
        this.dateTime = dateTime;
        this.plan = plan;
        this.count = count;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public boolean isPlan() {
        return plan;
    }

    public long getCount() {
        return count;
    }
}
