package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.adapter.Den;
import cz.cvut.fel.jankupat.AlkoApp.adapter.ProfileAdapter;
import cz.cvut.fel.jankupat.AlkoApp.dao.DayDao;
import cz.cvut.fel.jankupat.AlkoApp.exception.NotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.exception.ResourceNotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.*;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;
import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;
import cz.cvut.fel.jankupat.AlkoApp.rest.util.RestUtils;
import cz.cvut.fel.jankupat.AlkoApp.security.CurrentUser;
import cz.cvut.fel.jankupat.AlkoApp.security.UserPrincipal;
import cz.cvut.fel.jankupat.AlkoApp.service.DayService;
import cz.cvut.fel.jankupat.AlkoApp.service.ReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@RestController
@RequestMapping(path = "/day")
public class DayController extends BaseController<DayService, Day, DayDao> {
    private final ReflectionService reflectionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public DayController(DayService service, ReflectionService reflectionService) {
        super(service);
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
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", ((IEntity) entityToUpdate).getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }

    /**
     * @param id         of day
     * @param reflection body of new reflection
     * @return status 202
     */
    @PostMapping(value = "{id}/reflection", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDayAndAddToProfileDays(@PathVariable Integer id, @RequestBody Reflection reflection) {
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

    /**
     * Model of Day in android app is store different,
     * for this reason need to be covert to different model
     *
     * @param userPrincipal current user
     * @return Profile variables without relationships
     */
    @GetMapping("/adapter")
    public Collection<Den> getCurrentUserDays(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        Profile temp = user.getProfile();
        Collection<Day> days = temp.getDays();
        List<Den> dni = new LinkedList<>();

        days.forEach(day ->
        {
            String com = null;
            String feelings = null;
            String stickWithPlan = null;

            Reflection reflection = day.getReflection();
            if (reflection != null) {
                com = reflection.getComment();
                feelings = convertFeelings(reflection.getFeelings());
                stickWithPlan = reflection.getDifficulty();
            }

            dni.add(new Den(day.getName(), com,  day.getDateTime(), feelings, day.getId(), stickWithPlan));
        });

        return dni;
    }

    /**
     * Android app store a feeling as a number, which convert into span
     * it's easier to convert set, than string in a app, so for this reason
     * it's done on the server
     *
     * @param feelings set of enums of feelings
     * @return string with numbers
     */
    public static String convertFeelings(Set<FeelingsEnum> feelings) {
        StringBuilder response = new StringBuilder();

        for (FeelingsEnum feeling : feelings) {
            switch (feeling) {
                case GOOD:
                    response.append("1");
                    break;
                case GREAT:
                    response.append("2");
                    break;
                case CONFIDENT:
                    response.append("3");
                    break;
                case SAD:
                    response.append("4");
                    break;
                case ANGRY:
                    response.append("5");
                    break;
                case SHY:
                    response.append("6");
                    break;
                case DISGUSTED:
                    response.append("7");
                    break;
                case SURPRISED:
                    response.append("8");
                    break;
                case TERRIBLE:
                    response.append("9");
                    break;
            }
        }

        return response.toString();
    }

}