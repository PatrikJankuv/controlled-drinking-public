package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.UserDao;
import cz.cvut.fel.jankupat.AlkoApp.model.User;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
public class UserService extends BaseService<User, UserDao> {

    public UserService(UserDao dao){
        super(dao);
    }
}
