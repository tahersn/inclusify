package tn.esprit.eventservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.eventservice.Entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {

    @Query("select e from Event e where e.organizer = ?1")
    List<Event> getEventsByUser(String userId);
}
