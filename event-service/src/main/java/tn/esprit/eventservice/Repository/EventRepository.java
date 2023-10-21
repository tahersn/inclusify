package tn.esprit.eventservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.eventservice.Entity.Event;

public interface EventRepository extends JpaRepository<Event,Integer> {
}
