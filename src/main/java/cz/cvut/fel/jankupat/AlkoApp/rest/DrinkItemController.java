package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.adapter.Den;
import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemDao;
import cz.cvut.fel.jankupat.AlkoApp.exception.ResourceNotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.*;
import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;
import cz.cvut.fel.jankupat.AlkoApp.security.CurrentUser;
import cz.cvut.fel.jankupat.AlkoApp.security.UserPrincipal;
import cz.cvut.fel.jankupat.AlkoApp.service.DrinkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Drink item controller.
 *
 * @author Patrik Jankuv
 * @created 8 /4/2020
 */
@RestController
@RequestMapping(path = "/drink_item")
public class DrinkItemController extends BaseController<DrinkItemService, DrinkItem, DrinkItemDao> {
    @Autowired
    private UserRepository userRepository;

    /**
     * Instantiates a new Drink item controller.
     *
     * @param service the service
     */
    public DrinkItemController(DrinkItemService service){ super(service);}

    /**
     * Gets current user days.
     *
     * @param userPrincipal the user principal
     * @return the current user days
     */
    @GetMapping("/me")
    public Collection<DrinkItem> getCurrentUserDays(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        Collection<Day> days = user.getProfile().getDays();
        List<DrinkItem> items = new LinkedList<>();

        days.forEach(day -> {
            Collection<DrinkItem> dayItems = day.getItems();
            dayItems.forEach(item -> {items.add(item);});
        ;});

        return items;
    }

    /**
     * Gets current user days with drinks.
     *
     * @param userPrincipal the user principal
     * @return the current user days with drinks
     */
    @GetMapping("/adapter")
    public Collection<Den> getCurrentUserDaysWithDrinks(@CurrentUser UserPrincipal userPrincipal) {
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
                feelings = DayController.convertFeelings(reflection.getFeelings());
                stickWithPlan = reflection.getDifficulty();
            }

            dni.add(new Den(day.getName(), com,  day.getDateTime(), feelings, day.getId(), stickWithPlan, day.getItems()));
        });

        return dni;
    }
}
