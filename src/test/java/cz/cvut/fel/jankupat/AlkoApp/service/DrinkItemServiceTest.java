package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.Envioment.ObjectsGenerator;
import cz.cvut.fel.jankupat.AlkoApp.dao.DrinkItemDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Patrik Jankuv
 * @created 4/26/2021
 */
@RunWith(MockitoJUnitRunner.class)
class DrinkItemServiceTest {

    @Mock
    DrinkItemDao itemDao;

    @Mock
    DayService dayService;

    @InjectMocks
    DrinkItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.itemService = new DrinkItemService(itemDao, dayService);
    }

    @Test
    void countItemsAndCheckPlanTest() {
        System.out.println("countItemsAndCheckPlanTest");
        Day day = new Day();
        DrinkItem item1 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, true, day, "beer");
        DrinkItem item2 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, true, day, "beer");

        Set<DrinkItem> items = new HashSet<>();
        items.add(item1);
        items.add(item2);
        day.setItems(items);

        boolean result = itemService.countItemsAndCheckPlan(day);
        assertEquals(true, result);

        item1 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, false, day, "beer");
        item2 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, false, day, "beer");

        items.clear();
        items.add(item1);
        items.add(item2);
        day.setItems(items);

        result = itemService.countItemsAndCheckPlan(day);
        assertEquals(false, result);


        item1 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, true, day, "beer");
        item2 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, false, day, "beer");

        items.clear();
        items.add(item1);
        items.add(item2);
        day.setItems(items);

        result = itemService.countItemsAndCheckPlan(day);
        assertEquals(true, result);


        item1 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, false, day, "beer");
        item2 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, true, day, "beer");

        items.clear();
        items.add(item1);
        items.add(item2);
        day.setItems(items);

        result = itemService.countItemsAndCheckPlan(day);
        assertEquals(true, result);

        System.out.println("Count items test completed");
    }

    @Test
    void countItemsAndCheckPlanAdvancedTest() {
        System.out.println("countItemsAndCheckPlanAdvancedTest");
        Day day = new Day();
        DrinkItem item1 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, true, day, "beer");
        DrinkItem item2 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 8, false, day, "BEER");
        DrinkItem item3 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 4, true, day, "beer");
        DrinkItem item4 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 1, false, day, "spirit");
        DrinkItem item5 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, true, day, "beer");
        DrinkItem item6 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, true, day, "spirit");

        Set<DrinkItem> items = new HashSet<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        day.setItems(items);

        boolean result = itemService.countItemsAndCheckPlan(day);
        assertEquals(true, result);
        System.out.println("Count items advanced test completed");
    }

    @Test
    void persistTest() {
        System.out.println("persistTest");
        Day day = new Day();
        day.setId(1);
        DrinkItem item1 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, true, day, "beer");

        Set<DrinkItem> items = new HashSet<>();
        items.add(item1);
        day.setItems(items);
        when(dayService.find(item1.getDay().getId())).thenReturn(day);

        itemService.persist(item1);
        assertEquals(true, day.getPlanAccomplished());

        Day day2 = new Day();
        day2.setId(2);
        DrinkItem item2 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 4, false, day2, "beer");

        items = new HashSet<>();
        items.add(item2);
        day2.setItems(items);
        when(dayService.find(item2.getDay().getId())).thenReturn(day2);

        itemService.persist(item2);

        assertEquals(false, day2.getPlanAccomplished());
        System.out.println("persistTest completed");
    }

    @Test
    void updateTest(){
        System.out.println("update Test");
        Day day = new Day();
        day.setId(1);
        DrinkItem item1 = ObjectsGenerator.GenerateDrinkItemWithParameters(20.0, 50, 2, false, day, "beer");

        Set<DrinkItem> items = new HashSet<>();
        items.add(item1);
        day.setItems(items);
        when(dayService.find(item1.getDay().getId())).thenReturn(day);

        itemService.persist(item1);
        assertEquals(false, day.getPlanAccomplished());

        item1.setPlanned(true);
        itemService.update(item1);
        assertEquals(true, day.getPlanAccomplished());

        System.out.println("update test completed");

    }
}