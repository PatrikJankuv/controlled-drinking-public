package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DayDao;
import cz.cvut.fel.jankupat.AlkoApp.dao.FeelingDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Feeling;
import org.springframework.stereotype.Service;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Service
public class FeelingService extends BaseService<Feeling, FeelingDao> {

    public FeelingService(FeelingDao dao){
        super(dao);
    }
}
