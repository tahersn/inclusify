package tn.esprit.eventservice;

import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import tn.esprit.eventservice.Entity.CategoryEvent;
import tn.esprit.eventservice.Entity.Event;
import tn.esprit.eventservice.Entity.StatusEvent;
import tn.esprit.eventservice.Repository.CategoryRepository;
import tn.esprit.eventservice.Repository.EventRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
//@ComponentScan(basePackages = "tn.esprit.eventservice")
public class EventServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventServiceApplication.class, args);
	}

	/*@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:5000"); // Allow requests from your React frontend
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}*/

	@Bean
	ApplicationRunner init(EventRepository eventRepository, CategoryRepository categoryRepository) {
		return args -> {
			String uploadsDirectoryPath = "uploads";
			Path uploadsDirectory = Paths.get(uploadsDirectoryPath);
			if (!Files.exists(uploadsDirectory)) {
				try {
					Files.createDirectories(uploadsDirectory);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(uploadsDirectory.toAbsolutePath().toString());

			CategoryEvent category1 = new CategoryEvent("Category 1");
			CategoryEvent category2 = new CategoryEvent("Category 2");
			categoryRepository.save(category1);
			categoryRepository.save(category2);

			List<String> attendees = new ArrayList<>();
			attendees.add("1");  // Convert the Long to a String
			attendees.add("2");  // Convert the Long to a String

			Event event1 = new Event("Event 1", "Description 1", new Date(), "Location 1", "Organizer 1", StatusEvent.UPCOMING, 100, new Date(), new Date(), attendees, category1);
			event1.setCategory(category1);

			Event event2 = new Event("Event 2", "Description 2", new Date(), "Location 2", "Organizer 2", StatusEvent.UPCOMING, 150, new Date(), new Date(), attendees, category2);
			event2.setCategory(category2);

			eventRepository.save(event1);
			eventRepository.save(event2);
		};
	}
}
