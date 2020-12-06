package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.AchievementDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Achievement;
import org.springframework.stereotype.Service;

/**
 * The type Achievement service.
 *
 * @author Patrik Jankuv
 * @created 10 /2/2020
 */
@Service
public class AchievementService extends BaseService<Achievement, AchievementDao> {

    /**
     * Instantiates a new Achievement service.
     *
     * @param dao the dao
     */
    public AchievementService(AchievementDao dao){super(dao);}
}
