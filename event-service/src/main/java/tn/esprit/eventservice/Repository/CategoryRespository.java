package tn.esprit.eventservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.eventservice.Entity.CategoryEvent;

import java.util.List;

public interface CategoryRespository extends JpaRepository<CategoryEvent, Integer> {
    List<CategoryEvent> findAll();
}
