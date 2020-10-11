package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.exception.ResourceNotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.IEntity;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.User;
import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;
import cz.cvut.fel.jankupat.AlkoApp.rest.util.RestUtils;
import cz.cvut.fel.jankupat.AlkoApp.security.CurrentUser;
import cz.cvut.fel.jankupat.AlkoApp.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
//    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    /**
     *
     * @param userPrincipal current user
     * @return Profile connected to profile
     */
    @GetMapping("user/me/profile")
    public Profile getCurrentUserProfile(@CurrentUser UserPrincipal userPrincipal){
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        return user.getProfile();
    }

    /**
     *
     * @param userPrincipal source of current user data
     * @param profile object which gonna add to user
     * @return ACCEPTED response if add, if user has a profile return NOT_MODIFIED response
     */
    @PostMapping("user/me/profile")
    public ResponseEntity<Void> addProfileToUser(@CurrentUser UserPrincipal userPrincipal, @RequestBody Profile profile){
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        if(user.getProfile()!=null){
            LOG.debug("Profile already set {}", profile);
            final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", profile.getId());
            return new ResponseEntity<>(headers, HttpStatus.NOT_MODIFIED);
        }

        user.setProfile(profile);
        User result = userRepository.save(user);

        LOG.debug("Updated entity {}.", result);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", profile.getId());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }

    /**
     * Remove profile from user
     * @param userPrincipal current user
     * @return NO_CONTENT response
     */
    @DeleteMapping("user/me/profile")
    public ResponseEntity removeProfileFromUser(@CurrentUser UserPrincipal userPrincipal){
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        user.setProfile(null);

        User result = userRepository.save(user);

        LOG.debug("Updated entity {}.", result);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
