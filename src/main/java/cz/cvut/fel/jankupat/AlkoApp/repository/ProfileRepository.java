package cz.cvut.fel.jankupat.AlkoApp.repository;

import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Patrik Jankuv
 * @created 11/13/2020
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByName(String Name);

    List<Profile> findByNameStartsWithIgnoreCase(String name);

}
