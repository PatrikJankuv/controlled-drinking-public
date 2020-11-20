package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Service
public class DrinkItemService extends BaseService<DrinkItem, DrinkItemDao> {

    @Autowired
    public DrinkItemService(DrinkItemDao dao){
        super(dao);
    }

    @Override
    public void persist(DrinkItem object) {
        Objects.requireNonNull(object);
        if(!object.getPlanned()){
            object.setDateTime(LocalTime.now());
        }

        super.persist(object);
    }

    @Override
    public void update(DrinkItem object) {
        Objects.requireNonNull(object);
        if(!object.getPlanned()){
            object.setDateTime(LocalTime.now());
        }

        super.update(object);
    }

    public List<DrinkItem> getProfileDrinks(Profile profile, LocalDate date){
        Objects.requireNonNull(profile);
        return dao.getProfileItems(profile, date);
    }
}
