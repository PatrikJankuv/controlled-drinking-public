package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.FeelingDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Feeling;
import cz.cvut.fel.jankupat.AlkoApp.service.FeelingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@RestController
@RequestMapping(path = "/feeling")
public class FeelingController extends BaseController<FeelingService, Feeling, FeelingDao> {
    public FeelingController(FeelingService service){ super(service);}
}
