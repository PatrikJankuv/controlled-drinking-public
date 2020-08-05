package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemTimeDao;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItemTime;
import cz.cvut.fel.jankupat.AlkoApp.service.DrinkItemTimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@RestController
@RequestMapping(path = "/drink_item_time")
public class DrinkItemTimeController extends BaseController<DrinkItemTimeService, DrinkItemTime, DrinkItemTimeDao> {
    public DrinkItemTimeController(DrinkItemTimeService service){ super(service);}
}
