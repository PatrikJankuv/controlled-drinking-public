package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.ProfileDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.AchievementEnum;
import org.springframework.stereotype.Service;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Service
public class ProfileService extends BaseService<Profile, ProfileDao> {

    public ProfileService(ProfileDao dao){
        super(dao);
    }

    /**
     * Check if Profile contains achievement
     *
     * @param profile Profile
     * @param achievementEnum name of achievement
     * @return true if contains, false if not
     */
    public boolean containsAchievement(Profile profile, AchievementEnum achievementEnum){
        return profile.getAchievements().stream().anyMatch(p -> p.getName().equals(achievementEnum));
    }
}
