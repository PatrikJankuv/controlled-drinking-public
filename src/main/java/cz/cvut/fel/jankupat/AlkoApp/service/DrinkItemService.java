package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
        super.remove(object);

        Day day = dayService.find(object.getDay().getId());
        boolean plan = countItems(day);
        day.setPlanAccomplished(plan);
        dayService.update(day);
    }

    /**
     * Gets profile drinks.
     *
     * @param profile the profile
     * @param date    the date
     * @return the profile drinks
     */
    public List<DrinkItem> getProfileDrinks(Profile profile, LocalDate date) {
        Objects.requireNonNull(profile);
        return dao.getProfileItems(profile, date);
    }


    /**
     * after every edit of drink item count if day accomplished plan
     *
     * @param day Day from which drink is
     * @return true if plan is good, false if not
     */
    private boolean countItems(Day day) {
        Set<DrinkItem> items = day.getItems();
        int beer = 0;
        int wine = 0;
        int spirit = 0;
        int other = 0;
        int free = 0;

         if(items == null){
            return true;
        }

        for (DrinkItem item : items) {
            String type = item.getDrinkType();

            if (type.equals("BEER")) {
                if (item.getPlanned()) {
                    beer = beer - item.getCount();
                } else {
                    beer = beer + item.getCount();
                }
            } else if (type.equals("WINE")) {
                if (item.getPlanned()) {
                    wine = wine - item.getCount();
                } else {
                    wine = wine + item.getCount();
                }
            } else if (type.equals("NON_ALCOHOL")) {
                if (item.getPlanned()) {
                    free = free - item.getCount();
                } else {
                    free = free + item.getCount();
                }
            } else if (type.equals("SPIRIT")) {
                if (item.getPlanned()) {
                    spirit = spirit - item.getCount();
                } else {
                    spirit = spirit + item.getCount();
                }
            } else if (type.equals("OTHER")) {
                if (item.getPlanned()) {
                    other = other - item.getCount();
                } else {
                    other = other + item.getCount();
                }
            }
        }
        return beer <= 0 && wine <= 0 && free <= 0 && other <= 0 && spirit <= 0;
    }
}
