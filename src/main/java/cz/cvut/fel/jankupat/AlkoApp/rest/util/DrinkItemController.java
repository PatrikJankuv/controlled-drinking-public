package cz.cvut.fel.jankupat.AlkoApp.rest.util;

import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemDao;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.rest.BaseController;
import cz.cvut.fel.jankupat.AlkoApp.service.DrinkItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@RestController
@RequestMapping(path = "/drink_item")
public class DrinkItemController extends BaseController<DrinkItemService, DrinkItem, DrinkItemDao> {
    public DrinkItemController(DrinkItemService service){ super(service);}
}
