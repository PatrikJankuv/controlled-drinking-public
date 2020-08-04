package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import org.springframework.stereotype.Repository;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Repository
public class DayDao extends BaseDao<Day> {

    public DayDao(){
        super(Day.class);
    }
}