package cz.cvut.fel.jankupat.AlkoApp;

import cz.cvut.fel.jankupat.AlkoApp.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
//@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class AlkoAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AlkoAppApplication.class, args);
	}

}
