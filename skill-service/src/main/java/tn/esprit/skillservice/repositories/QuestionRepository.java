package tn.esprit.skillservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.skillservice.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
