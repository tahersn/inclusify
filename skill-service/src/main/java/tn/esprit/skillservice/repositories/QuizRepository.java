package tn.esprit.skillservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.skillservice.entities.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
