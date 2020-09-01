package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.ProfileDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
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
}
