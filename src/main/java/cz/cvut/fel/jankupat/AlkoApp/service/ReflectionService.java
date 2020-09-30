package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.ReflectionDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Reflection;
import org.springframework.stereotype.Service;

/**
 * @author Patrik Jankuv
 * @created 9/30/2020
 */

@Service
public class ReflectionService extends BaseService<Reflection, ReflectionDao> {
    public ReflectionService(ReflectionDao dao) {
        super(dao);
    }
}
