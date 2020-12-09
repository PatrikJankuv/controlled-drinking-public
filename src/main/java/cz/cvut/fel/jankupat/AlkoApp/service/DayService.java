package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DayDao;
import cz.cvut.fel.jankupat.AlkoApp.dao.ReflectionDao;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.Reflection;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * The type Day service.
 *
 * @author Patrik Jankuv
 * @created 8 /4/2020
 */
@Service
public class DayService extends BaseService<Day, DayDao> {
    /**
     * The Reflection dao.
     */
    ReflectionDao reflectionDao;

    /**
     * Instantiates a new Day service.
     *
     * @param dao           the dao
     * @param reflectionDao the reflection dao
     */
    public DayService(DayDao dao, ReflectionDao reflectionDao) {
        super(dao);
        this.reflectionDao = reflectionDao;
    }

    /**
     * Gets stats.
     *
     * @param period the period
     * @return the stats
     */
    public List<DayStatsAdapter> getStats(int period) {
        LocalDate now = LocalDate.now();
        LocalDate lastWeek = now.minusDays(period);
        return dao.getStatsFilterDate(lastWeek, now);
    }

    /**
     * Gets stats filter.
     *
     * @param period       the period
     * @param bottomAge    the bottom age
     * @param topAge       the top age
     * @param gender       the gender
     * @param smoker       the smoker
     * @param bottomWeight the bottom weight
     * @param topWeight    the top weight
     * @param bottomHeight the bottom height
     * @param topHeight    the top height
     * @return the stats filter
     */
    public List<DayStatsAdapter> getStatsFilter(int period, Integer bottomAge, Integer topAge,
                                                Set<String> gender,
                                                Set<String> smoker,
                                                Integer bottomWeight, Integer topWeight,
                                                Integer bottomHeight, Integer topHeight) {
        LocalDate now = LocalDate.now();
        LocalDate lastWeek = now.minusDays(period);

        String male = "null";
        String female = "null";
        String other = "null";

        if (gender.size() == 3 || gender.size() == 0) {
            male = "MALE";
            female = "FEMALE";
            other = "OTHER";
        } else {
            for (String g : gender) {
                if (g.equals("Male")) {
                    male = "MALE";
                } else if (g.equals("Female")) {
                    female = "FEMALE";
                } else {
                    other = "OTHER";
                }
            }
        }

        String yes = "null";
        String no = "null";
        String occasionally = "null";

        if (smoker.size() == 3 || smoker.size() == 0) {
            yes = "YES";
            no = "NO";
            occasionally = "OCCASIONALLY";
        } else {
            for (String g : smoker) {
                if (g.equals("Yes")) {
                    yes = "YES";
                } else if (g.equals("No")) {
                    no = "NO";
                } else {
                    occasionally = "OCCASIONALLY";
                }
            }
        }


        if (bottomAge == null) {
            bottomAge = 0;
        }
        if (topAge == null) {
            topAge = 110;
        }
        if (bottomWeight == null) {
            bottomWeight = 0;
        }
        if (topWeight == null) {
            topWeight = 500;
        }
        if (bottomHeight == null) {
            bottomHeight = 0;
        }
        if (topHeight == null) {
            topHeight = 240;
        }
        return dao.getStatsFilter(lastWeek, now, bottomAge, topAge, male, female, other, yes, no, occasionally, bottomWeight, topWeight, bottomHeight, topHeight);
    }

    /**
     * Gets feelings for profile.
     *
     * @param profile the profile
     * @param dt      the dt
     * @return the feelings for profile
     */
    public Reflection getFeelingsForProfile(Profile profile, LocalDate dt) {
        Day day = dao.getDayForProfile(profile, dt);
        Reflection reflection = dao.getReflectionForDay(day);
        return reflection;
    }

    /**
     * Gets day for profile.
     *
     * @param profile the profile
     * @param dt      the dt
     * @return the day for profile
     */
    public Day getDayForProfile(Profile profile, LocalDate dt) {
        return dao.getDayForProfile(profile, dt);
    }

    /**
     * @param profile
     * @return
     */
    public TreeMap<String, Integer> getReflectionsForProfile(Profile profile) {
        List<Reflection> reflections = dao.getReflectionsForProfile(profile);

        TreeMap<String, Integer> tmap = new TreeMap<String, Integer>();
        for (Reflection r : reflections) {
        Set<FeelingsEnum> list = r.getFeelings();
            for (FeelingsEnum t : list) {
                Integer c = tmap.get(t.toString());
                tmap.put(t.toString(), (c == null) ? 1 : c + 1);
            }
        }

        return tmap;
    }
}
