package tn.esprit.eventservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.eventservice.Entity.Event;
import tn.esprit.eventservice.Repository.EventRespository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventRespository eventRepository;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event savedEvent = eventRepository.save(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> updateEvent(@PathVariable Integer eventId, @RequestBody Event updatedEvent) {
        Optional<Event> existingEventOptional = eventRepository.findById(eventId);

        if (!existingEventOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Event existingEvent = existingEventOptional.get();

        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setOrganizer(updatedEvent.getOrganizer());
        existingEvent.setStatus(updatedEvent.getStatus());
        existingEvent.setCapacity(updatedEvent.getCapacity());
        existingEvent.setRegistrationDeadline(updatedEvent.getRegistrationDeadline());
        existingEvent.setImage(updatedEvent.getImage());
        existingEvent.setCreationDate(updatedEvent.getCreationDate());

        Event updated = eventRepository.save(existingEvent);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);

        if (!eventOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        eventRepository.deleteById(eventId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAllEvents() {
        eventRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
