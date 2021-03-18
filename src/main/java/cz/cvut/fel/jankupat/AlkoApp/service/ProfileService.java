package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.ProfileDao;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.ProfileStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The type Profile service.
 *
 * @author Patrik Jankuv
 * @created 8 /3/2020
 */
@Service
public class ProfileService extends BaseService<Profile, ProfileDao> {

    /**
     * Instantiates a new Profile service.
     *
     * @param dao the dao
     */
    @Autowired
    public ProfileService(ProfileDao dao) {
        super(dao);

    }

    /**
     * Check if Profile contains achievement
     *
     * @param profile         Profile
     * @param achievementEnum name of achievement
     * @return true if contains, false if not
     */
    public boolean containsAchievement(Profile profile, String achievementEnum) {
        return profile.getAchievements().stream().anyMatch(p -> p.getName().equals(achievementEnum));
    }

    /**
     * Gets gender stats.
     *
     * @return count genders in map
     */
    public Map<String, Integer> getGenderStats() {
        int maleCount = 0;
        int femaleCount = 0;
        int otherCount = 0;
        int smoke = 0;
        int nosmoke = 0;
        int ocasionally = 0;

        List<Profile> profiles = dao.findAll();

        for (Profile profile : profiles) {
            if (profile.getGender().equals("MALE")) {
                maleCount++;
            } else if (profile.getGender().equals("FEMALE")) {
                femaleCount++;
            } else {
                otherCount++;
            }

            if (profile.getSmoker().equals("YES")) {
                smoke++;
            } else if (profile.getSmoker().equals("NO")) {
                nosmoke++;
            } else {
                ocasionally++;
            }
        }

        Map<String, Integer> response = new HashMap<>();
        response.put("Muž", maleCount);
        response.put("Žena", femaleCount);
        response.put("Jiné", otherCount);
        response.put("smoke", smoke);
        response.put("nosmoke", nosmoke);
        response.put("ocas_smoke", ocasionally);
        return response;
    }

    /**
     * Gets planned items.
     *
     * @param profile the profile
     * @return the planned items
     */
    public List<DrinkItem> getPlannedItems(Profile profile) {
        Collection<Day> days = profile.getDays();


        List<DrinkItem> items = new LinkedList<>();
        return items;
    }

    /**
     * Get stats list.
     *
     * @return the list
     */
    public List<ProfileStatsAdapter> getStats(){
        return dao.getStats();
    }
}
