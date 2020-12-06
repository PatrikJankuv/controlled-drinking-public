package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.model.AuthProvider;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.User;
import cz.cvut.fel.jankupat.AlkoApp.payload.AuthResponse;
import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;
import cz.cvut.fel.jankupat.AlkoApp.security.TokenProvider;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * The type Free auth controller.
 *
 * @author Patrik Jankuv
 * @created 10 /11/2020
 */
@RestController
@RequestMapping("/auth/free")
public class FreeAuthController {
    /**
     * The Log.
     */
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private ProfileService profileService;

    /**
     * Create profile response entity.
     *
     * @param profile the profile
     * @return the response entity
     */
    @PostMapping("/profile")
    public ResponseEntity<?> createProfile(@RequestBody Profile profile){
        User user = new User();
        long count = userRepository.count();

        int number = (int)count + 2;
        String email = "temp" + number + "@alcoapp.com";
        String name = generateRandomString(7);
        String password = generateRandomString(7);

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setProvider(AuthProvider.generated);

        profileService.persist(profile);
        user.setProfile(profile);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        //give time db
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));


    }

    /**
     * Generate random string string.
     *
     * @param len the len
     * @return the string
     */
    public String generateRandomString(int len) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
