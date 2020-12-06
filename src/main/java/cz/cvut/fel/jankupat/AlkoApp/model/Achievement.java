package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;


/**
 * The type Achievement.
 *
 * @author Patrik Jankuv
 * @created 10 /2/2020
 */
@Entity
public class Achievement extends BaseEntity implements IEntity {

    @Column(nullable = false)
    private String name;

    private LocalDateTime dateTime;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets date time.
     *
     * @return the date time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets date time.
     *
     * @param dateTime the date time
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
