package tn.esprit.eventservice.Entity;

import javax.persistence.*;
import java.util.*;

import tn.esprit.eventservice.Entity.CategoryEvent;
import tn.esprit.eventservice.Model.User;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String location;

    @Column(name = "organizer")
    private String organizer;


    @Enumerated(EnumType.STRING)
    private StatusEvent status;

    private Integer capacity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDeadline;

    private String image;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;


    @ElementCollection
    @CollectionTable(name = "event_attendees", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "attendee_id")
    private List<String> attendeeIds;



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

    public String getImage() {
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

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setAttendeeIds(Set<String> attendeeIds) {
        this.attendeeIds = new ArrayList<>(attendeeIds);
    }

    public void setCategory(CategoryEvent category) {
        this.category = category;
    }

    // Constructors

    public Event() {
    }


    public Event(String name, String description, Date date, String location, String organizer, StatusEvent status, int capacity, Date registrationDeadline, String image, Date creationDate, List<String> attendeeIds, CategoryEvent category) {
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
}
