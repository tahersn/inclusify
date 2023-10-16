package tn.esprit.eventservice.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Long> attendeeIds = new HashSet<>();

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

    public Set<Long> getAttendeeIds() {
        return attendeeIds;
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

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
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

    public void setAttendeeIds(Set<Long> attendeeIds) {
        this.attendeeIds = attendeeIds;
    }

    // Constructors

    public Event() {
    }

    public Event(String name, String description, Date date, String location, String organizer, StatusEvent status, Integer capacity, Date registrationDeadline, String image, Date creationDate, Set<Long> attendeeIds) {
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
    }
}
