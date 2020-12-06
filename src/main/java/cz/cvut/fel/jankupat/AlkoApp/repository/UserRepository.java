package cz.cvut.fel.jankupat.AlkoApp.repository;

import cz.cvut.fel.jankupat.AlkoApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);

    /**
     * Find by email starts with ignore case list.
     *
     * @param email the email
     * @return the list
     */
    List<User> findByEmailStartsWithIgnoreCase(String email);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    Boolean existsByEmail(String email);

}
