package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.User;
import org.springframework.stereotype.Repository;


/**
 * The type User dao.
 *
 * @author Patrik Jankuv
 * @created 10 /12/2020
 */
@Repository
public class UserDao extends  BaseDao<User> {

    /**
     * Instantiates a new User dao.
     */
    public UserDao() {
        super(User.class);
    }

}
