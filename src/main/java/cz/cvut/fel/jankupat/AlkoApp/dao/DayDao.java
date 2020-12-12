package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.Reflection;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Day dao.
 *
 * @author Patrik Jankuv
 * @created 8 /4/2020
 */
@Repository
public class DayDao extends BaseDao<Day> {
    /**
     * Instantiates a new Day dao.
     */
    public DayDao() {
        super(Day.class);
    }

    /**
     * Gets stats.
     *
     * @return count accomplished plans for date
     */
    public List<DayStatsAdapter> getStats() {
        List items = em.createQuery("SELECT new cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter(d.dateTime, d.planAccomplished, count(d.dateTime)) " +
                "from Day d GROUP BY d.planAccomplished, d.dateTime ORDER BY d.dateTime DESC").getResultList();

        return items;
    }

    public List<Day> getForSpecificProfileDaysInRange(Profile profile, LocalDate since, LocalDate to){
        List<Day> items = em.createQuery("SELECT d FROM Profile p INNER JOIN p.days d WHERE p.id = ?1 AND d.dateTime >= ?2 AND d.dateTime <= ?3 ORDER BY d.dateTime ASC", Day.class).setParameter(1, profile.getId()).setParameter(2, since).setParameter(3, to).getResultList();
        return items;
    }

    /**
     * Gets stats filter date.
     *
     * @param since the since
     * @param to    the to
     * @return count accomplished plans for date
     */
    public List<DayStatsAdapter> getStatsFilterDate(LocalDate since, LocalDate to) {
        List items = em.createQuery("SELECT new cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter(d.dateTime, d.planAccomplished, count(d.dateTime)) " +
                "from Day d GROUP BY d.planAccomplished, d.dateTime " +
                "HAVING d.dateTime > ?1 AND d.dateTime < ?2 ORDER BY d.dateTime ASC")
                .setParameter(1, since)
                .setParameter(2, to)
                .getResultList();

        return items;
    }

    /**
     * Gets stats filter.
     *
     * @param since        the since
     * @param to           the to
     * @param bottomAge    the bottom age
     * @param topAge       the top age
     * @param male         the male
     * @param female       the female
     * @param other        the other
     * @param yes          the yes
     * @param no           the no
     * @param occasionally the occasionally
     * @param bottomWeight the bottom weight
     * @param topWeight    the top weight
     * @param bottomHeight the bottom height
     * @param topHeight    the top height
     * @return the stats filter
     */
    public List<DayStatsAdapter> getStatsFilter(LocalDate since, LocalDate to, Integer bottomAge, Integer topAge,
                                                String male, String female, String other,
                                                String yes, String no, String occasionally,
                                                Integer bottomWeight, Integer topWeight,
                                                Integer bottomHeight, Integer topHeight) {
        List items = em.createQuery("SELECT new cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter(d.dateTime, d.planAccomplished, count(d.dateTime)) " +
                "FROM Profile p INNER JOIN p.days d " +
                "WHERE p.age >= ?5 AND  p.age <= ?4" +
                "AND (p.gender = ?6  OR p.gender = ?7 OR p.gender = ?8)" +
                "AND (p.smoker = ?9  OR p.smoker = ?10 OR p.smoker = ?11)" +
                "AND p.weight >= ?12 AND p.weight <= ?13" +
                "AND p.height >= ?14 AND p.height <= ?3" +
                "GROUP BY d.planAccomplished, d.dateTime " +
                "HAVING d.dateTime > ?1 AND d.dateTime <= ?2 " +
                "ORDER BY d.dateTime ASC")
                .setParameter(1, since).setParameter(2, to)
                .setParameter(5, bottomAge).setParameter(4, topAge)
                .setParameter(6, male).setParameter(7, female).setParameter(8, other)
                .setParameter(9, yes).setParameter(10, no).setParameter(11, occasionally)
                .setParameter(12, bottomWeight).setParameter(13, topWeight)
                .setParameter(14, bottomHeight).setParameter(3, topHeight)
                .getResultList();
//        System.out.println(since + ", " + to  + ", " +  bottomAge  + ", " + topAge  + ", " + male + ", " + female + ", " + other + ", " + yes + ", " + no + ", " + occasionally + ", " + bottomWeight + ", " + topWeight + ", " + bottomHeight + ", " + topHeight);
        return items;
    }

    /**
     * Gets day for profile.
     *
     * @param profile the profile
     * @param dt      the dt
     * @return the day for profile
     */
    public Day getDayForProfile(Profile profile, LocalDate dt) {
        Day item = em.createQuery("SELECT d from Profile p INNER JOIN p.days d WHERE p.id = ?1 AND d.dateTime = ?2", Day.class).setParameter(1, profile.getId()).setParameter(2, dt).getSingleResult();
        return item;
    }

    /**
     * Gets reflection for day.
     *
     * @param day the day
     * @return the reflection for day
     */
    public Reflection getReflectionForDay(Day day) {
        Reflection item = em.createQuery("SELECT d from Day p INNER JOIN p.reflection d WHERE p.id = ?1 ", Reflection.class).setParameter(1, day.getId()).getSingleResult();
        return item;
    }
    /**
     * Gets reflection for day.
     *
     * @param profile the profile
     * @return the reflection for day
     */
    public List<Reflection> getReflectionsForProfile(Profile profile) {
        List<Reflection> items = em.createQuery("SELECT r from Profile p INNER JOIN p.days d INNER JOIN d.reflection r WHERE p.id = ?1 ", Reflection.class).setParameter(1, profile.getId()).getResultList();

        return items;
    }
}