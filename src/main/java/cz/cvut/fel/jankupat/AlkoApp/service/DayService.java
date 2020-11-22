package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DayDao;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Service
public class DayService extends BaseService<Day, DayDao> {

    public DayService(DayDao dao){
        super(dao);
    }

    public List<DayStatsAdapter> getStats(){
        return dao.getStats();
    }
}
