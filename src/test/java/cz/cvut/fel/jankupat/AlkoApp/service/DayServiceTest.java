package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.repository.UserRepository;
import cz.cvut.fel.jankupat.AlkoApp.controller.DayController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(DayController.class)
class DayServiceTest {

    @Autowired
    private DayService service;

    @MockBean
    private UserRepository repository;

    @Test
    void round() {
        assertEquals(2.53, DayService.round(2.5297, 2));
    }

//    @Test
//    public void testGiveAll()
//            throws Exception {
//
//        Day day1 = new Day();
//
//        List<Day> allEmployees = Arrays.asList(day1);
//
//        given(service.findAll()).willReturn(allEmployees);
//
//        mvc.perform(get("/day")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(alex.getName())));
//    }
}