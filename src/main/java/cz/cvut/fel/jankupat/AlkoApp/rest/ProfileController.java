package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.adapter.ProfileAdapter;
import cz.cvut.fel.jankupat.AlkoApp.dao.ProfileDao;
import cz.cvut.fel.jankupat.AlkoApp.exception.NotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.exception.ResourceNotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.*;
import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;
import cz.cvut.fel.jankupat.AlkoApp.rest.util.RestUtils;
import cz.cvut.fel.jankupat.AlkoApp.security.CurrentUser;
import cz.cvut.fel.jankupat.AlkoApp.security.UserPrincipal;
import cz.cvut.fel.jankupat.AlkoApp.service.AchievementService;
import cz.cvut.fel.jankupat.AlkoApp.service.BaseService;
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
    private UserRepository userRepository;

    @Autowired
    public ProfileController(ProfileService service, DayService dayService, AchievementService achievementService){ super(service);
        this.dayService = dayService;
        this.achievementService = achievementService;
    }

    /**
     * override method for updating object
     * @param entityToUpdate source of information, from form
     * @param   id of edit entity
     * @return status 201
     */
    @Override
    public ResponseEntity<Void> updateEntity(@RequestBody Profile entityToUpdate, @PathVariable("id") Integer id) {
        Profile profile = this.service.find(id);

        profile.setName(entityToUpdate.getName());
        profile.setAge(entityToUpdate.getAge());
        profile.setGender(entityToUpdate.getGender());
        profile.setHeight(entityToUpdate.getHeight());
        profile.setSmoker(entityToUpdate.getSmoker());
        profile.setWeight(entityToUpdate.getWeight());

        this.service.update(profile);
        LOG.debug("Updated entity {}.", entityToUpdate);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", ((IEntity)entityToUpdate).getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
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

        if(!service.containsAchievement(profile, achievement.getName())){

            achievement.setDateTime(LocalDateTime.now());
            achievementService.persist(achievement);
            profile.addAchievement(achievement);

            service.update(profile);
            LOG.debug("Updated entity {}.", profile);
            final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", achievement.getId());
            return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
        }
        else {
            LOG.debug("Profile contains achievement {}.", profile);
            final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", profile.getId());
            return new ResponseEntity<>(headers, HttpStatus.NOT_MODIFIED);
        }
    }

    /**
     * @param userPrincipal current user
     * @return Profile variables without relationships
     */
    @GetMapping("/me")
    public ProfileAdapter getCurrentUserProfile(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        Profile temp = user.getProfile();

        return new ProfileAdapter(temp.getName(), temp.getGender(), temp.getWeight(), temp.getHeight(), temp.getAge(), temp.getSmoker());
    }

    @PutMapping("/me")
    public ResponseEntity<Void> updateCurrentProfile(@CurrentUser UserPrincipal userPrincipal, @RequestBody Profile entityToUpdate) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        Profile profile = user.getProfile();
        profile.setName(entityToUpdate.getName());
        profile.setAge(entityToUpdate.getAge());
        profile.setGender(entityToUpdate.getGender());
        profile.setHeight(entityToUpdate.getHeight());
        profile.setSmoker(entityToUpdate.getSmoker());
        profile.setWeight(entityToUpdate.getWeight());

        service.update(profile);

        LOG.debug("Updated entity {}.", profile);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", ((IEntity)profile).getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }

    /**
     *
     * @param userPrincipal current user
     * @param day new day which gonna add
     * @return
     */
    @PostMapping("/day")
    public ResponseEntity<Void> addDayCurrentProfile(@CurrentUser UserPrincipal userPrincipal, @RequestBody Day day) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        Profile temp = user.getProfile();

        dayService.persist(day);
        temp.addDay(day);
        service.update(temp);

        LOG.debug("Updated entity {}.", temp);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", day.getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }
}