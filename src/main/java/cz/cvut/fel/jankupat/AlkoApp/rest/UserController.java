package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.UserDao;
import cz.cvut.fel.jankupat.AlkoApp.model.User;
import cz.cvut.fel.jankupat.AlkoApp.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@RestController
@RequestMapping(path = "/user")
public class UserController extends BaseController<UserService, User, UserDao> {
    public UserController(UserService service){ super(service);}
}