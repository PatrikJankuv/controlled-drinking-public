package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.exception.BaseException;
import cz.cvut.fel.jankupat.AlkoApp.exception.ResourceNotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.AuthProvider;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.User;
import cz.cvut.fel.jankupat.AlkoApp.payload.AuthResponse;
import cz.cvut.fel.jankupat.AlkoApp.payload.SignUpRequest;
import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;
import cz.cvut.fel.jankupat.AlkoApp.rest.util.RestUtils;
import cz.cvut.fel.jankupat.AlkoApp.security.CurrentUser;
import cz.cvut.fel.jankupat.AlkoApp.security.TokenProvider;
import cz.cvut.fel.jankupat.AlkoApp.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type User controller.
 */
@RestController
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Gets current user.
     *
     * @param userPrincipal the user principal
     * @return the current user
     */
    @GetMapping("/user/me")
//    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    /**
     * Get current user profile profile.
     *
     * @param userPrincipal current user
     * @return Profile connected to profile
     */
    @GetMapping("/user/me/profile")
    public Profile getCurrentUserProfile(@CurrentUser UserPrincipal userPrincipal){
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));


        Profile temp = user.getProfile();
        Profile response = new Profile();
        response.setName(temp.getName());
        response.setWeight(temp.getWeight());


        return user.getProfile();
    }

    /**
     * Add profile to user response entity.
     *
     * @param userPrincipal source of current user data
     * @param profile       object which gonna add to user
     * @return ACCEPTED response if add, if user has a profile return NOT_MODIFIED response
     */
    @PostMapping("/user/me/profile")
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
     * Sign up with existing profile response entity.
     *
     * @param userPrincipal source of current user data
     * @param signUpRequest sign up credentials
     * @return ACCEPTED response if add, if user has a profile return NOT_MODIFIED response
     */
    @PostMapping("/user/signup")
    public ResponseEntity<?> signUpWithExistingProfile(@CurrentUser UserPrincipal userPrincipal, @Valid @RequestBody SignUpRequest signUpRequest){
        User oldUser = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));


        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BaseException("Email address already in use.");
        }

        Profile profile = oldUser.getProfile();
        oldUser.setProfile(null);
        userRepository.save(oldUser);
        userRepository.delete(oldUser);

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setProfile(profile);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        signUpRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }


    /**
     * Remove profile from user
     *
     * @param userPrincipal current user
     * @return NO_CONTENT response
     */
    @DeleteMapping("/user/me/profile")
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
