package tn.esprit.skillservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.skillservice.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
