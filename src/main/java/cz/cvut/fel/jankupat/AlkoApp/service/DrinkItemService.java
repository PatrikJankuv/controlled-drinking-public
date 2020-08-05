package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemDao;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import org.springframework.stereotype.Service;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Service
public class DrinkItemService extends BaseService<DrinkItem, DrinkItemDao> {

    public DrinkItemService(DrinkItemDao dao){
        super(dao);
    }
}
