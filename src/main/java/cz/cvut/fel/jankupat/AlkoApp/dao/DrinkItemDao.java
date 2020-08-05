package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import org.springframework.stereotype.Repository;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Repository
public class DrinkItemDao extends BaseDao<DrinkItem> {

    public DrinkItemDao(){
        super(DrinkItem.class);
    }
}