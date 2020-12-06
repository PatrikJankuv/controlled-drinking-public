package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;

import javax.persistence.*;
import java.util.Set;

/**
 * The type Reflection.
 *
 * @author Patrik Jankuv
 * @created 9 /30/2020
 */
@Entity
@Table(name = "REFLECTION")
public class Reflection extends BaseEntity implements IEntity{
    private String difficulty;

    private String comment;

    @ElementCollection(targetClass = FeelingsEnum.class, fetch = FetchType.EAGER)
    @JoinTable(name = "feelings", joinColumns = @JoinColumn(name = "reflection_id"))
    @Column(name = "feeling", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<FeelingsEnum> feelings;

    /**
     * Gets difficulty.
     *
     * @return the difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets difficulty.
     *
     * @param difficulty the difficulty
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets feelings.
     *
     * @return the feelings
     */
    public Set<FeelingsEnum> getFeelings() {
        return feelings;
    }

    /**
     * Sets feelings.
     *
     * @param feelings the feelings
     */
    public void setFeelings(Set<FeelingsEnum> feelings) {
        this.feelings = feelings;
    }
}
