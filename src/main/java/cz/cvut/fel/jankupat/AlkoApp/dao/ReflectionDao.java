package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.Reflection;
import org.springframework.stereotype.Repository;

/**
 * The type Reflection dao.
 *
 * @author Patrik Jankuv
 * @created 9 /30/2020
 */
@Repository
public class ReflectionDao extends BaseDao<Reflection> {
    /**
     * Instantiates a new Reflection dao.
     */
    public ReflectionDao() {
        super(Reflection.class);
    }
}
