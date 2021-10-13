package cz.cvut.fel.jankupat.AlkoApp.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Profile test.
 *
 * @author Patrik Jankuv
 * @created 4 /26/2021
 */
class ProfileTest {


    /**
     * Add day.
     */
    @Test
    void addDay() {
        Profile the_profile = new Profile();
        Day testDay = new Day();
        Day testDay2 = new Day();

        List<Day> days = new ArrayList<>();
        days.add(testDay);
        the_profile.addDay(testDay);
        assertEquals(days, the_profile.getDays());

        days.add(testDay2);
        the_profile.addDay(testDay2);
        assertEquals(days, the_profile.getDays());
    }
}