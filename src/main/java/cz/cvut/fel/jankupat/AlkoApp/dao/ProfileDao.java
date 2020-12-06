package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.dao.util.ProfileStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Profile dao.
 *
 * @author Patrik Jankuv
 * @created 8 /3/2020
 */
@Repository
public class ProfileDao extends BaseDao<Profile> {

    /**
     * Instantiates a new Profile dao.
     */
    public ProfileDao() {
        super(Profile.class);
    }

    /**
     * Gets day.
     *
     * @param profile the profile
     * @param dt      the dt
     * @return the day
     */
    public Day getDay(Profile profile, LocalDate dt) {
        Day items = em.createQuery("SELECT d from Profile p INNER JOIN p.days d WHERE p.id = ?1 AND d.dateTime = ?2", Day.class).setParameter(1, profile.getId()).setParameter(2, dt).getSingleResult();

        return items;
    }

    /**
     * Get stats list.
     *
     * @return the list
     */
    public List<ProfileStatsAdapter> getStats(){
        List<ProfileStatsAdapter> stats = em.createQuery("SELECT new cz.cvut.fel.jankupat.AlkoApp.dao.util.ProfileStatsAdapter(avg(p.age), AVG(p.weight), AVG(p.height), p.gender) FROM Profile p GROUP BY p.gender ORDER BY p.gender ASC ", ProfileStatsAdapter.class).getResultList();
        return stats;
    }
}
