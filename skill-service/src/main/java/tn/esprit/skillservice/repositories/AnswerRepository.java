package tn.esprit.skillservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.skillservice.entities.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
