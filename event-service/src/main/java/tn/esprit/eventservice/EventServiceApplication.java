package tn.esprit.eventservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.esprit.eventservice.Entity.CategoryEvent;
import tn.esprit.eventservice.Entity.Event;
import tn.esprit.eventservice.Entity.StatusEvent;
import tn.esprit.eventservice.Repository.CategoryRespository;
import tn.esprit.eventservice.Repository.EventRespository;

import java.util.*;

@SpringBootApplication
public class EventServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner init(EventRespository eventRepository, CategoryRespository categoryRepository) {
		return args -> {
			// Create sample categories
			CategoryEvent category1 = new CategoryEvent("Category 1");
			CategoryEvent category2 = new CategoryEvent("Category 2");
			categoryRepository.save(category1);
			categoryRepository.save(category2);

			// Create sample events
			List<String> attendees = new ArrayList<>();
			attendees.add("1");  // Convert the Long to a String
			attendees.add("2");  // Convert the Long to a String


			Event event1 = new Event("Event 1", "Description 1", new Date(), "Location 1", "Organizer 1", StatusEvent.UPCOMING, 100, new Date(), "Image 1", new Date(), attendees, category1);
			event1.setCategory(category1);

			Event event2 = new Event("Event 2", "Description 2", new Date(), "Location 2", "Organizer 2", StatusEvent.UPCOMING, 150, new Date(), "Image 2", new Date(), attendees, category2);
			event2.setCategory(category2);

			eventRepository.save(event1);
			eventRepository.save(event2);
		};
	}
}