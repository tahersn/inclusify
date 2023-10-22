package tn.esprit.skillservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.skillservice.entities.Skill;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

    @Query("select s from Skill s where s.id in (SELECT q.skill.id FROM Question q GROUP BY q.skill.id HAVING COUNT(q.skill.id) >= 3)")
    List<Skill> getPlayableSkills();
}
