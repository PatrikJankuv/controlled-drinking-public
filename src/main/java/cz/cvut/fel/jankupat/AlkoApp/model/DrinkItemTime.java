package cz.cvut.fel.jankupat.AlkoApp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Entity
public class DrinkItemTime extends BaseEntity implements IEntity{

    @ManyToOne(cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(nullable = false)
    private DrinkItem drinkItem;

    private LocalTime dateTime;
}
