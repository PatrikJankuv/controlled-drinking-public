package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Drink item dao.
 *
 * @author Patrik Jankuv
 * @created 8 /4/2020
 */
@Repository
public class DrinkItemDao extends BaseDao<DrinkItem> {

    /**
     * Instantiates a new Drink item dao.
     */
    public DrinkItemDao() {
        super(DrinkItem.class);
    }

    /**
     * Gets profile items.
     *
     * @param profile Profile
     * @param dt      Date
     * @return Profile 's drinkitems drank in Date
     */
    public List<DrinkItem> getProfileItems(Profile profile, LocalDate dt) {
        Day items = em.createQuery("SELECT d from Profile p INNER JOIN p.days d WHERE p.id = ?1 AND d.dateTime = ?2", Day.class).setParameter(1, profile.getId()).setParameter(2, dt).getSingleResult();

        return new ArrayList<>(items.getItems());
    }
}