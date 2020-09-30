package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Patrik Jankuv
 * @created 9/30/2020
 */
@Entity
@Table(name = "REFLECTION")
public class Reflection extends BaseEntity implements IEntity{
    private String difficulty;

    private String comment;

    @ElementCollection(targetClass = FeelingsEnum.class)
    @JoinTable(name = "feelings", joinColumns = @JoinColumn(name = "reflection_id"))
    @Column(name = "feeling", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<FeelingsEnum> feelings;

    @OneToOne(mappedBy = "reflection")
    private Day day;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<FeelingsEnum> getFeelings() {
        return feelings;
    }

    public void setFeelings(Set<FeelingsEnum> feelings) {
        this.feelings = feelings;
    }
}
