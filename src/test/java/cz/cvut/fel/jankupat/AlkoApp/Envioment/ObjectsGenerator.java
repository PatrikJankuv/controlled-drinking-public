package cz.cvut.fel.jankupat.AlkoApp.Envioment;

import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;

/**
 * @author Patrik Jankuv
 * @created 4/26/2021
 */
public class ObjectsGenerator {

    public static DrinkItem GenerateDrinkItemWithParameters(double alcohol, int amount, int count, boolean planned, Day day, String drinkType){
        DrinkItem drinkItem = new DrinkItem();
        drinkItem.setPlanned(planned);
        drinkItem.setAmount(amount);
        drinkItem.setCount(count);
        drinkItem.setAlcohol(alcohol);
        drinkItem.setDay(day);
        drinkItem.setDrinkType(drinkType);
        return drinkItem;
    }
}
