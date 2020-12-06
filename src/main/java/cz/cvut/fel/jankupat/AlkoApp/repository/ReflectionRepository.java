package cz.cvut.fel.jankupat.AlkoApp.repository;

import cz.cvut.fel.jankupat.AlkoApp.model.Reflection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Reflection repository.
 *
 * @author Patrik Jankuv
 * @created 11 /13/2020
 */
public interface ReflectionRepository extends JpaRepository<Reflection, Long> {
}
