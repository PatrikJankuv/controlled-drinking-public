package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemDao;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.ProfileDrinkItemStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The type Drink item service.
 *
 * @author Patrik Jankuv
 * @created 8 /4/2020
 */
@Service
public class DrinkItemService extends BaseService<DrinkItem, DrinkItemDao> {

    /**
     * The Day service.
     */
    DayService dayService;

    /**
     * Instantiates a new Drink item service.
     *
     * @param dao        the dao
     * @param dayService the day service
     */
    @Autowired
    public DrinkItemService(DrinkItemDao dao, DayService dayService) {
        super(dao);
        this.dayService = dayService;
    }

    @Override
    public void persist(DrinkItem object) {
        Objects.requireNonNull(object);
        if (!object.getPlanned()) {
            object.setDateTime(LocalTime.now());
        }
        super.persist(object);

        Day day = dayService.find(object.getDay().getId());
        boolean plan = countItems(day);
        day.setPlanAccomplished(plan);
        dayService.update(day);
    }

    @Override
    public void update(DrinkItem object) {
        Objects.requireNonNull(object);
        if (!object.getPlanned()) {
            object.setDateTime(LocalTime.now());
        }

        super.update(object);


        Day day = dayService.find(object.getDay().getId());
        boolean plan = countItems(day);
        day.setPlanAccomplished(plan);
        dayService.update(day);
    }

    @Override
    public void remove(DrinkItem object) {
        Day day = dayService.find(object.getDay().getId());
        Set<DrinkItem> items = day.getItems();
        items.remove(object);

        boolean plan = countItems(day);
        day.setPlanAccomplished(plan);
        dayService.update(day);

        super.remove(object);
    }

    /**
     * Gets profile drinks for specific day.
     *
     * @param profile the profile
     * @param date    the date
     * @return the profile drinks
     */
    public List<DrinkItem> getProfileDrinksForSpecificDay(Profile profile, LocalDate date) {
        Objects.requireNonNull(profile);
        return dao.getProfileItemsForSpecificDay(profile, date);
    }

    /**
     * @param profile the profile
     * @return p
     */
    public List<ProfileDrinkItemStatsAdapter> getProfileDrinks(Profile profile) {
        Objects.requireNonNull(profile);
        return dao.getProfileItems(profile);
    }

    public List<ProfileDrinkItemStatsAdapter> getStatsAboutAllProfiles() {
        return dao.getStatsAboutAllProfiles();
    }


    /**
     * after every edit of drink item count if day accomplished plan
     *
     * @param day Day from which drink is
     * @return true if plan is good, false if not
     */
    private boolean countItems(Day day) {
        Set<DrinkItem> items = day.getItems();

        if (items == null) {
            return true;
        }

        HashMap<String, Integer> countItems = new HashMap<>();

        for (DrinkItem item : items) {
            String type = item.getDrinkType();
            try {
                int count = countItems.get(type);

                if (item.getPlanned()) {
                    count = count - item.getCount();
                } else {
                    count = count + item.getCount();
                }
                countItems.put(type, count);
            } catch (Exception e) {
                countItems.put(type, item.getCount());
            }
        }

        for (Integer i : countItems.values()) {
            if (i > 0) {
                return false;
            }
        }
        return true;
    }
}
