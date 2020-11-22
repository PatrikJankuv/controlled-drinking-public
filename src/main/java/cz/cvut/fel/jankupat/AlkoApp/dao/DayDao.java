package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Repository
public class DayDao extends BaseDao<Day> {
    public DayDao(){
        super(Day.class);
    }

    /**
     *
     * @return count accomplished plans for date
     */
    public List<DayStatsAdapter> getStats(){
        List items = em.createQuery("SELECT new cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter(d.dateTime, d.planAccomplished, count(d.dateTime)) " +
                "from Day d GROUP BY d.planAccomplished, d.dateTime").getResultList();

        return items;
    }
}