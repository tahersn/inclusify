package tn.esprit.skillservice.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.skillservice.entities.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("select q from Question q where q.skill.id = ?1 ORDER BY RAND()")
    List<Question> getQuestionsBySkill(int skillId, Pageable pageable);

}
