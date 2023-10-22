package tn.esprit.skillservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.skillservice.entities.Quiz;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    @Query("select q.skill.id from Quiz q where q.user_id=?1 AND q.isSuccessful=true")
    List<Integer> getQuizByUser(String userId);
}
