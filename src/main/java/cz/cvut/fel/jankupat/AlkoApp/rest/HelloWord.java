package cz.cvut.fel.jankupat.AlkoApp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Hello word.
 *
 * @author Patrik Jankuv
 * @created 7 /20/2020
 */
//@RestController
//@RequestMapping("")
public class HelloWord {

    /**
     * Hello word string.
     *
     * @return the string
     */
    @GetMapping
    public String helloWord(){
        return "Hello alko app user";
    }

    /**
     * How are you string.
     *
     * @return the string
     */
    @GetMapping
    @RequestMapping(value = "/hiy")
    public String howAreYou(){
        return "How are you?";
    }
}
