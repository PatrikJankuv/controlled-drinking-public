package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import org.springframework.stereotype.Repository;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Repository
public class ProfileDao extends BaseDao<Profile> {

    public ProfileDao() {
        super(Profile.class);
    }
}
