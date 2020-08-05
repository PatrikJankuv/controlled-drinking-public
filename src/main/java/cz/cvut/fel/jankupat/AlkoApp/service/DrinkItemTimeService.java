package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemTimeDao;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItemTime;
import org.springframework.stereotype.Service;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Service
public class DrinkItemTimeService extends BaseService<DrinkItemTime, DrinkItemTimeDao> {

    public DrinkItemTimeService(DrinkItemTimeDao dao){
        super(dao);
    }
}
