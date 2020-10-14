package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.adapter.ProfileAdapter;
import cz.cvut.fel.jankupat.AlkoApp.exception.ResourceNotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.User;
import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;
import cz.cvut.fel.jankupat.AlkoApp.security.CurrentUser;
import cz.cvut.fel.jankupat.AlkoApp.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrik Jankuv
 * @created 10/14/2020
 */
@RestController
@RequestMapping(path = "/adapter")
public class AdapterController {
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    /**
     * @param userPrincipal current user
     * @return Profile variables without relationships
     */
    @GetMapping("user/me/profile")
    public ProfileAdapter getCurrentUserProfile(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        Profile temp = user.getProfile();

        return new ProfileAdapter(temp.getName(), temp.getGender(), temp.getWeight(), temp.getHeight(), temp.getAge(), temp.getSmoker());
    }
}

