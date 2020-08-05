package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.AchievementDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Achievement;
import org.springframework.stereotype.Service;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Service
public class AchievementService extends BaseService<Achievement, AchievementDao> {

    public AchievementService(AchievementDao dao){
        super(dao);
    }
}
