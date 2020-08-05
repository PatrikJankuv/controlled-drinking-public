package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.Feeling;
import org.springframework.stereotype.Repository;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Repository
public class FeelingDao extends BaseDao<Feeling> {

    public FeelingDao(){super(Feeling.class);
    }
}