package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemDao;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
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
}
