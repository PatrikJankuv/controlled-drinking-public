package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.DayDao;
import cz.cvut.fel.jankupat.AlkoApp.exception.NotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.IEntity;
import cz.cvut.fel.jankupat.AlkoApp.model.Reflection;
import cz.cvut.fel.jankupat.AlkoApp.rest.util.RestUtils;
import cz.cvut.fel.jankupat.AlkoApp.service.DayService;
import cz.cvut.fel.jankupat.AlkoApp.service.ReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
//todo check whats going on
@RestController
@RequestMapping(path = "/day")
public class DayController extends BaseController<DayService, Day, DayDao> {
    private final ReflectionService reflectionService;

    @Autowired
    public DayController(DayService service, ReflectionService reflectionService){ super(service);
        this.reflectionService = reflectionService;
    }

    @Override
    public ResponseEntity<Void> updateEntity(@RequestBody Day entityToUpdate, @PathVariable("id") Integer id) {
        Day den = this.service.find(id);

        den.setDateTime(entityToUpdate.getDateTime());
        den.setDescription(entityToUpdate.getDescription());
        den.setName(entityToUpdate.getName());

        this.service.update(den);
        LOG.debug("Updated entity {}.", entityToUpdate);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", ((IEntity)entityToUpdate).getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }

    /**
     *
     * @param id of day
     * @param reflection body of new reflection
     * @return status 202
     */
    @PostMapping(value = "{id}/reflection", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDayAndAddToProfileDays(@PathVariable Integer id, @RequestBody Reflection reflection){
        Day day = service.find(id);

        if (day == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }

        reflectionService.persist(reflection);
        day.setReflection(reflection);
        service.update(day);

        LOG.debug("Updated entity {}.", day);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", reflection.getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }
}