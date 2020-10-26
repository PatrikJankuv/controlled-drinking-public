package cz.cvut.fel.jankupat.AlkoApp.adapter;

import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.model.enums.FeelingsEnum;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * This class helps to create a model from Den in android app
 *
 * @author Patrik Jankuv
 * @created 10/26/2020
 */
public class Den {
    public Den(String name, String desc, LocalDate datetime, String feelings, Integer serverId, String stickWithPlan) {
        this.name = name;
        this.desc = desc;
        this.datetime = datetime;
        this.feelings = feelings;
        this.serverId = serverId;
        this.stickWithPlan = stickWithPlan;
    }

    public Den(String name, String desc, LocalDate datetime, String feelings, Integer serverId, String stickWithPlan, Set<DrinkItem> items) {
        this.name = name;
        this.desc = desc;
        this.datetime = datetime;
        this.feelings = feelings;
        this.serverId = serverId;
        this.stickWithPlan = stickWithPlan;
        this.items = items;
    }

    public String name;

    public String desc;

    public LocalDate datetime;

    public String feelings;

    public Integer serverId;

    public String stickWithPlan;

    public Set<DrinkItem> items;
}
