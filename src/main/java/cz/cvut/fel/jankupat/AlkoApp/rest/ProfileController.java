package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.ProfileDao;
import cz.cvut.fel.jankupat.AlkoApp.exception.NotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.Achievement;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.IEntity;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.rest.util.RestUtils;
import cz.cvut.fel.jankupat.AlkoApp.service.AchievementService;
import cz.cvut.fel.jankupat.AlkoApp.service.DayService;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@RestController
@RequestMapping(path = "/profile")
public class ProfileController extends BaseController<ProfileService, Profile, ProfileDao> {
    private final DayService dayService;
    private final AchievementService achievementService;

    @Autowired
    public ProfileController(ProfileService service, DayService dayService, AchievementService achievementService){ super(service);
        this.dayService = dayService;
        this.achievementService = achievementService;
    }

    @Override
    public ResponseEntity<Void> updateEntity(Profile entityToUpdate, Integer id) {
        Profile profile = this.service.find(id);
        System.out.println("update");
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", ((IEntity)entityToUpdate).getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }

    //    //todo vyries aby sa nemazali dni, ked sa updatuje
//    @Override
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> updateEntity(Profile entityToUpdate, Integer id) {
//        final Profile entity = service.find(id);
//        System.out.println("a");
//        if (entity == null) {
//            throw NotFoundException.create(this.getClass().getSimpleName(), id);
//        }
//        System.out.println("b");
//        ((IEntity)entityToUpdate).setId(((IEntity)entity).getId());
////        if(!(entity.getDays() == null)){
////            entityToUpdate.setDays(entity.getDays());
////        }
//        System.out.println("c");
//        service.update(entityToUpdate);
//        System.out.println("d");
//        LOG.debug("Updated entity {}.", entityToUpdate);
//        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", ((IEntity)entityToUpdate).getId());
//        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
//    }

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

    /**
     * Add achievement and set time for profile
     *
     * @param id of Profile
     * @param achievement Achievement
     * @return response
     */
    @PostMapping(value = "{id}/achievement", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addAchievement(@PathVariable Integer id, @RequestBody Achievement achievement){
        Profile profile = service.find(id);
        achievement.setDateTime(LocalDateTime.now());

        achievementService.persist(achievement);
        profile.addAchievement(achievement);
        service.update(profile);

        LOG.debug("Updated entity {}.", profile);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", achievement.getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }
}