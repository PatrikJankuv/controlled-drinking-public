package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItemTime;
import org.springframework.stereotype.Repository;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Repository
public class DrinkItemTimeDao extends BaseDao<DrinkItemTime> {

    public DrinkItemTimeDao(){
        super(DrinkItemTime.class);
    }

    public DrinkItemTime findDrinkItemTimeAccordingDrink(DrinkItem drinkItem){
        return em.createQuery("SELECT t FROM DrinkItemTime t where t.drinkItem = :item", DrinkItemTime.class).setParameter("item", drinkItem).getResultList().get(0);
    }
}
