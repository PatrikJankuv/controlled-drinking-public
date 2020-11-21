package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Repository
public class ProfileDao extends BaseDao<Profile> {

    public ProfileDao() {
        super(Profile.class);
    }

    public Day getDay(Profile profile, LocalDate dt) {
        Day items = em.createQuery("SELECT d from Profile p INNER JOIN p.days d WHERE p.id = ?1 AND d.dateTime = ?2", Day.class).setParameter(1, profile.getId()).setParameter(2, dt).getSingleResult();

        return items;
    }
}
