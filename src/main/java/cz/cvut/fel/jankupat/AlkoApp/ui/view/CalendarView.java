package cz.cvut.fel.jankupat.AlkoApp.ui.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import cz.cvut.fel.jankupat.AlkoApp.model.DrinkItem;
import cz.cvut.fel.jankupat.AlkoApp.service.DrinkItemService;
import cz.cvut.fel.jankupat.AlkoApp.service.ProfileService;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;

import java.time.LocalDate;
import java.util.*;
import java.util.List;

/**
 * @author Patrik Jankuv
 * @created 11/15/2020
 */

@EnableVaadin({"com.example.application", "org.vaadin.stefan"})
@Route(value = "calendar", layout = MainLayout.class)
public class CalendarView extends VerticalLayout implements HasUrlParameter<Integer> {

    private Grid<DrinkItem> planned = new Grid<>(DrinkItem.class);
    private Grid<DrinkItem> drunk = new Grid<>(DrinkItem.class);

    private ProfileService profileService;
    private DrinkItemService drinkItemService;
    private Label label = new Label();
    private Icon suc = new Icon(VaadinIcon.SMILEY_O);
    Icon fail = new Icon(VaadinIcon.FROWN_O);
    Span text = new Span();

    Integer profileId;

    public CalendarView(ProfileService profileService, DrinkItemService service) {
        this.profileService = profileService;
        this.drinkItemService = service;

        add(dayToolbar());
        HorizontalLayout horizontalLayout = new HorizontalLayout(configPlanned(), configureDrank());

//        Icon fla = new Icon(VaadinIcon.CHECK_CIRCLE);
//        Span text = new Span("  success");

//        label.add(fla, text);
//        add(label);
        suc.setColor("#28965a");
        horizontalLayout.setWidthFull();
        add(horizontalLayout);
    }

    private Component dayToolbar(){

        DatePicker datePicker = new DatePicker();
        datePicker.setLabel("Select a day");
        datePicker.setPlaceholder("Date within " + "this month");
        datePicker.addValueChangeListener(event -> {
            if (event.getValue() == null) {
            } else {
                LocalDate dt = event.getValue();
                hry(dt);
            }
        });

        text.setText(" success");
        label.add(suc, text);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(datePicker, label);
        horizontalLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        return horizontalLayout;
    }

    private Component configureDrank(){
        H4 h2Planned = new H4("Consumed");

        drunk.addClassName("contact-grid");
        drunk.setWidthFull();
        drunk.setColumns("name", "drinkType", "price", "amount", "alcohol");
        drunk.getColumns().forEach(col -> col.setAutoWidth(true));

        VerticalLayout verticalLayout = new VerticalLayout(h2Planned, drunk);
        verticalLayout.setWidthFull();
        return verticalLayout;
    }

    private Component configPlanned(){
        H4 h2Planned = new H4("Planned");

        planned.addClassName("contact-grid");
        planned.setWidthFull();
        planned.setColumns("name", "drinkType", "price", "amount", "alcohol");
        planned.getColumns().forEach(col -> col.setAutoWidth(true));

        VerticalLayout verticalLayout = new VerticalLayout(h2Planned, planned);
        verticalLayout.setWidthFull();
        return verticalLayout;
    }

    private void hry(LocalDate dateTime){
        List<DrinkItem> planned = new LinkedList<>();
        List<DrinkItem> drank = new LinkedList<>();

        List<DrinkItem> items = drinkItemService.getProfileDrinks(profileService.find(1), dateTime);
        for(DrinkItem item:items){
            if(item.getPlanned()){
                planned.add(item);
            }else {
                drank.add(item);
            }
        }
        this.planned.setItems(items);
        this.drunk.setItems(drank);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer profileId) {
        this.profileId = profileId;
    }
}
