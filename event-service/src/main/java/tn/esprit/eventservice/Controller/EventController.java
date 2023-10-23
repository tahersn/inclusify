package tn.esprit.eventservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.eventservice.Entity.Event;
import tn.esprit.eventservice.Repository.EventRepository;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;
import tn.esprit.eventservice.Service.ImageUploadService;
import tn.esprit.eventservice.Service.SendEventInformationEmail;

@RestController
@RequestMapping("/event")
//@CrossOrigin(origins = "http://localhost:5000")
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ImageUploadService imageUploadService;
    @Autowired
    private SendEventInformationEmail sendEventInformationEmail;


    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEventById(@PathVariable(value = "eventId") Integer eventId) {
        return new ResponseEntity<>(eventRepository.findById(eventId).orElse(null), HttpStatus.OK);
    }


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> createEvent(@ModelAttribute Event event, @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            String imageUrl = imageUploadService.uploadImage(imageFile);
            event.setImage(imageUrl);
            Event savedEvent = eventRepository.save(event);
            return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{eventId}/join")
    public ResponseEntity<Event> joinEvent(@PathVariable Integer eventId, @RequestParam String userEmail) {
        System.out.println("*******************Here we Test the JOIN 1 ****************");
        System.out.println(userEmail);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (!eventOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Event event = eventOptional.get();
        if (event.getAttendeeIds().contains(userEmail)) {
            return ResponseEntity.badRequest().body(null);
        }
        System.out.println("*******************Here we Test the JOIN 2 ****************");
        event.getAttendeeIds().add(userEmail);
        event.getAttendeeEmails().add(userEmail);
        Event updatedEvent = eventRepository.save(event);
        sendEventInformationEmail.sendEventInformationEmail(userEmail, updatedEvent);
        return ResponseEntity.ok(updatedEvent);
    }


    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Integer eventId, @RequestBody Event updatedEvent) {
        Optional<Event> existingEventOptional = eventRepository.findById(eventId);

        if (!existingEventOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Event existingEvent = existingEventOptional.get();

        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setLocation(updatedEvent.getLocation());

        Event updatedEventEntity = eventRepository.save(existingEvent);

        return ResponseEntity.ok(updatedEventEntity);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer eventId) {
        eventRepository.deleteById(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllEvents() {
        eventRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
