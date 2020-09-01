package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.AchievementDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Achievement;
import cz.cvut.fel.jankupat.AlkoApp.service.AchievementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@RestController
@RequestMapping(path = "/achievement")
public class AchievementController extends BaseController<AchievementService, Achievement, AchievementDao> {
    public AchievementController(AchievementService service){ super(service);}
}
