package cz.cvut.fel.jankupat.AlkoApp.adapter;

import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;

import java.time.LocalDate;
import java.util.Set;

/**
 * This class helps to create a model from Den in android app
 *
 * @author Patrik Jankuv
 * @created 10 /26/2020
 */
public class Den {
    /**
     * Instantiates a new Den.
     *
     * @param name          the name
     * @param desc          the desc
     * @param datetime      the datetime
     * @param feelings      the feelings
     * @param serverId      the server id
     * @param stickWithPlan the stick with plan
     */
    public Den(String name, String desc, LocalDate datetime, String feelings, Integer serverId, String stickWithPlan) {
        this.name = name;
        this.desc = desc;
        this.datetime = datetime;
        this.feelings = feelings;
        this.serverId = serverId;
        this.stickWithPlan = stickWithPlan;
    }

    /**
     * Instantiates a new Den.
     *
     * @param name          the name
     * @param desc          the desc
     * @param datetime      the datetime
     * @param feelings      the feelings
     * @param serverId      the server id
     * @param stickWithPlan the stick with plan
     * @param items         the items
     */
    public Den(String name, String desc, LocalDate datetime, String feelings, Integer serverId, String stickWithPlan, Set<DrinkItem> items) {
        this.name = name;
        this.desc = desc;
        this.datetime = datetime;
        this.feelings = feelings;
        this.serverId = serverId;
        this.stickWithPlan = stickWithPlan;
        this.items = items;
    }

    /**
     * The Name.
     */
    public String name;

    /**
     * The Desc.
     */
    public String desc;

    /**
     * The Datetime.
     */
    public LocalDate datetime;

    /**
     * The Feelings.
     */
    public String feelings;

    /**
     * The Server id.
     */
    public Integer serverId;

    /**
     * The Stick with plan.
     */
    public String stickWithPlan;

    /**
     * The Items.
     */
    public Set<DrinkItem> items;
}
