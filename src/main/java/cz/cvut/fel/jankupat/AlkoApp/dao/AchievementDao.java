package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.Achievement;
import org.springframework.stereotype.Repository;

/**
 * @author Patrik Jankuv
 * @created 10/2/2020
 */
@Repository
public class AchievementDao extends BaseDao<Achievement> {

    public AchievementDao() {
        super(Achievement.class);
    }
}
