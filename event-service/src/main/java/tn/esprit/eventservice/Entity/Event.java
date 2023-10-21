package tn.esprit.eventservice.Entity;

import javax.persistence.*;
import java.io.File;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String location;
    private String meet;

    @Column(name = "organizer")
    private String organizer;

    @Enumerated(EnumType.STRING)
    private StatusEvent status;

    private Integer capacity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDeadline;

    private String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @ElementCollection
    @CollectionTable(name = "event_attendees", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "attendee_id")
    private List<String> attendeeIds;

    @ElementCollection
    @CollectionTable(name = "event_attendee_emails", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "attendee_email")
    private List<String> attendeeEmails;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEvent category;


    // getters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public StatusEvent getStatus() {
        return status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }


    public String  getImage() {
        return image;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public CategoryEvent getCategory() {
        return category;
    }

    public List<String> getAttendeeIds() {
        return attendeeIds;
    }

    public List<String> getAttendeeEmails() {
        return attendeeEmails;
    }

//setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(StatusEvent status) {
        this.status = status;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }


    public void setImage(String  image) {
        this.image = image;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCategory(CategoryEvent category) {
        this.category = category;
    }

    public void setAttendeeIds(List<String> attendeeIds) {
        this.attendeeIds = attendeeIds;
    }

    // Constructors

    public Event() {
    }

    public Event(Integer id, String name, String description, Date date, String location, String organizer, StatusEvent status, Integer capacity, Date registrationDeadline, String image, Date creationDate, List<String> attendeeIds, CategoryEvent category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.organizer = organizer;
        this.status = status;
        this.capacity = capacity;
        this.registrationDeadline = registrationDeadline;
        this.image = image;
        this.creationDate = creationDate;
        this.attendeeIds = attendeeIds;
        this.category = category;
    }

    public Event(String name, String description, Date date, String location, String organizer, StatusEvent status, int capacity, Date registrationDeadline, Date creationDate, List<String> attendeeIds, CategoryEvent category) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.organizer = organizer;
        this.status = status;
        this.capacity = capacity;
        this.registrationDeadline = registrationDeadline;
        this.creationDate = creationDate;
        this.attendeeIds = attendeeIds;
        this.category = category;
    }

    public String getMeet() {
        return meet;
    }

    public void setMeet(String meet) {
        this.meet = meet;
    }

}
