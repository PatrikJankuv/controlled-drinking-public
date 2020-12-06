package cz.cvut.fel.jankupat.AlkoApp;

import cz.cvut.fel.jankupat.AlkoApp.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The type Alko app application.
 */
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
//@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class AlkoAppApplication extends SpringBootServletInitializer {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		SpringApplication.run(AlkoAppApplication.class, args);
	}

}
