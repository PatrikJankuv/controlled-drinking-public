package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.DayDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.service.DayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
//todo check whats going on
//@RestController
//@RequestMapping(path = "/day")
public class DayController extends BaseController<DayService, Day, DayDao> {
    public DayController(DayService service){ super(service);}
}