package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.DayDao;
import cz.cvut.fel.jankupat.AlkoApp.dao.ReflectionDao;
import cz.cvut.fel.jankupat.AlkoApp.dao.util.DayStatsAdapter;
import cz.cvut.fel.jankupat.AlkoApp.model.Day;
import cz.cvut.fel.jankupat.AlkoApp.model.Profile;
import cz.cvut.fel.jankupat.AlkoApp.model.Reflection;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@Service
public class DayService extends BaseService<Day, DayDao> {
    ReflectionDao reflectionDao;

    public DayService(DayDao dao, ReflectionDao reflectionDao) {
        super(dao);
        this.reflectionDao = reflectionDao;
    }

    public List<DayStatsAdapter> getStats() {
        LocalDate now = LocalDate.now();
        LocalDate lastWeek = now.minusDays(7);
        return dao.getStatsFilterDate(lastWeek, now);
    }

    public List<DayStatsAdapter> getStatsFilter(Integer bottomAge, Integer topAge,
                                                Set<String> gender,
                                                Set<String> smoker,
                                                Integer bottomWeight, Integer topWeight,
                                                Integer bottomHeight, Integer topHeight) {
        LocalDate now = LocalDate.now();
        LocalDate lastWeek = now.minusDays(7);

        String male = "null";
        String female = "null";
        String other = "null";

        if(gender.size() == 3 || gender.size() == 0){
            male = "MALE";
            female = "FEMALE";
            other = "OTHER";
        }else{
            for(String g:gender){
                if(g.equals("Male")){
                    male = "MALE";
                }else if(g.equals("Female")){
                    female = "FEMALE";
                }else{
                    other = "OTHER";
                }
            }
        }

        String yes = "null";
        String no = "null";
        String occasionally = "null";

        if(smoker.size() == 3 || smoker.size() == 0){
            yes = "YES";
            no = "NO";
            occasionally = "OCCASIONALLY";
        }else{
            for(String g:smoker){
                if(g.equals("Yes")){
                    yes = "YES";
                }else if(g.equals("No")){
                    no = "NO";
                }else{
                    occasionally = "OCCASIONALLY";
                }
            }
        }


        if (bottomAge == null) {
            bottomAge = 0;
        }
        if (topAge == null) {
            topAge = 110;
        }
        if (bottomWeight == null) {
            bottomWeight = 0;
        }
        if (topWeight == null) {
            topWeight = 500;
        }
        if (bottomHeight == null) {
            bottomHeight = 0;
        }
        if (topHeight == null) {
            topHeight = 240;
        }
        return dao.getStatsFilter(lastWeek, now, bottomAge, topAge, male, female, other, yes, no, occasionally, bottomWeight, topWeight, bottomHeight, topHeight);
    }

    public Reflection getFeelingsForProfile(Profile profile, LocalDate dt) {
        Day day = dao.getDayForProfile(profile, dt);
        Reflection reflection = dao.getReflectionForDay(day);
        return reflection;
    }

    public Day getDayForProfile(Profile profile, LocalDate dt) {
        return dao.getDayForProfile(profile, dt);
    }
}
