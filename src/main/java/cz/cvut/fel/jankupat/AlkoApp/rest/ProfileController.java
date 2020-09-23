package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.ProfileDao;
import cz.cvut.fel.jankupat.AlkoApp.exception.NotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.IEntity;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.rest.util.RestUtils;
import cz.cvut.fel.jankupat.AlkoApp.service.DayService;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
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
@RestController
@RequestMapping(path = "/profile")
public class ProfileController extends BaseController<ProfileService, Profile, ProfileDao> {
    private final DayService dayService;

    @Autowired
    public ProfileController(ProfileService service, DayService dayService){ super(service);
        this.dayService = dayService;
    }

    /**
     * Create a day and add to collection of days User
     *
     * @param id id of Profile
     * @param day body of day, which gonna create
     * @return response 202
     */
    @PostMapping(value = "/{id}/day", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDayAndAddToProfileDays(@PathVariable Integer id, @RequestBody Day day){
        Profile profile = service.find(id);
        if (profile == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }

        dayService.persist(day);
        profile.addDay(day);
        service.update(profile);

        LOG.debug("Updated entity {}.", profile);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", day.getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }
}