package cz.cvut.fel.jankupat.AlkoApp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrik Jankuv
 * @created 7/20/2020
 */

@RestController
@RequestMapping("")
public class HelloWord {

    @GetMapping
    public String helloWord(){
        return "Hello alko app user";
    }
}
