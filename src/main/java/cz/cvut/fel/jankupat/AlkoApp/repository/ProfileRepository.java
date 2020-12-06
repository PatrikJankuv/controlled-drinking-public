package cz.cvut.fel.jankupat.AlkoApp.repository;

import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Profile repository.
 *
 * @author Patrik Jankuv
 * @created 11 /13/2020
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    /**
     * Find by name optional.
     *
     * @param Name the name
     * @return the optional
     */
    Optional<Profile> findByName(String Name);

    /**
     * Find by name starts with ignore case list.
     *
     * @param name the name
     * @return the list
     */
    List<Profile> findByNameStartsWithIgnoreCase(String name);

}
