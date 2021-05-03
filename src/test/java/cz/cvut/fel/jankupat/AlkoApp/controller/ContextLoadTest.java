package cz.cvut.fel.jankupat.AlkoApp.controller;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Patrik Jankuv
 * @created 5/3/2021
 */

@SpringBootTest
public class ContextLoadTest {
    @Autowired
    private AdapterController adapterController;

    @Autowired
    private AuthController authController;

    @Autowired
    private DayController dayController;

    @Autowired
    private DrinkItemController drinkItemController;

    @Autowired
    private FreeAuthController freeAuthController;

    @Autowired
    private ProfileController profileController;

    @Autowired
    private ReflectionController reflectionController;

    @Autowired
    private UserController userController;

    @Test
    public void contextLoad() throws Exception{
        assertThat(adapterController).isNotNull();
        assertThat(authController).isNotNull();
        assertThat(dayController).isNotNull();
        assertThat(drinkItemController).isNotNull();
        assertThat(freeAuthController).isNotNull();
        assertThat(profileController).isNotNull();
        assertThat(reflectionController).isNotNull();
        assertThat(userController).isNotNull();
    }
}
