package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.AchievementEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;


/**
 * @author Patrik Jankuv
 * @created 10/2/2020
 */
@Entity
public class Achievement extends BaseEntity implements IEntity {

    @Column(nullable = false)
    private AchievementEnum name;

    private LocalDateTime dateTime;

    public AchievementEnum getName() {
        return name;
    }

    public void setName(AchievementEnum name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
