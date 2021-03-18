package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.ReflectionDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Reflection;
import cz.cvut.fel.jankupat.AlkoApp.service.ReflectionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Reflection controller.
 *
 * @author Patrik Jankuv
 * @created 9 /30/2020
 */
@RestController
@RequestMapping(path = "/reflection")
public class ReflectionController extends BaseController<ReflectionService, Reflection, ReflectionDao> {
    /**
     * Instantiates a new Reflection controller.
     *
     * @param service the service
     */
    public ReflectionController(ReflectionService service) {
        super(service);
    }
}
