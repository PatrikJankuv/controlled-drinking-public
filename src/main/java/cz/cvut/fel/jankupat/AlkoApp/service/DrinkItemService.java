package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemDao;
import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemTimeDao;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItemTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Objects;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Service
public class DrinkItemService extends BaseService<DrinkItem, DrinkItemDao> {

    protected DrinkItemTimeDao drinkItemTimeDao;

    @Autowired
    public DrinkItemService(DrinkItemDao dao, DrinkItemTimeDao drinkItemTimeDao){
        super(dao);
        this.drinkItemTimeDao = drinkItemTimeDao;
    }

    @Override
    public void persist(DrinkItem object) {
        super.persist(object);

        if(object.getPlanned()){
            DrinkItemTime time = new DrinkItemTime();
            time.setDrinkItem(object);
            time.setDateTime(LocalTime.now());
            drinkItemTimeDao.persist(time);
        }
    }

    @Override
    public void update(DrinkItem object) {
        super.update(object);
        Objects.requireNonNull(object);

        if(object.getPlanned()){
            DrinkItemTime time = new DrinkItemTime();
            time.setDrinkItem(object);
            time.setDateTime(LocalTime.now());
            drinkItemTimeDao.persist(time);
        }
    }
}
