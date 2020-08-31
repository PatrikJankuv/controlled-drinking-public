package cz.cvut.fel.jankupat.AlkoApp;

import cz.cvut.fel.jankupat.AlkoApp.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class AlkoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlkoAppApplication.class, args);
	}

}
