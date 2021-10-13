package cz.cvut.fel.jankupat.AlkoApp.Envioment;

import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;

/**
 * The type Objects generator.
 *
 * @author Patrik Jankuv
 * @created 4 /26/2021
 */
public class ObjectsGenerator {

    /**
     * Generate drink item with parameters drink item.
     *
     * @param alcohol   the alcohol
     * @param amount    the amount
     * @param count     the count
     * @param planned   the planned
     * @param day       the day
     * @param drinkType the drink type
     * @return the drink item
     */
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
