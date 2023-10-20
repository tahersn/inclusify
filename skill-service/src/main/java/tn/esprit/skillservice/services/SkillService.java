package tn.esprit.skillservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.skillservice.entities.Skill;
import tn.esprit.skillservice.repositories.SkillRepository;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public Skill getSkill(int id){
        if (skillRepository.findById(id).isPresent())
            return skillRepository.findById(id).get();
        else
            return null;
    }

    public Skill addSkill(Skill skill){
        return skillRepository.save(skill);
    }

    public Skill updateSkill(int id, Skill newSkill){
        if (skillRepository.findById(id).isPresent()){
            Skill existingSkill = skillRepository.findById(id).get();
            existingSkill.setName(newSkill.getName());
            existingSkill.setQuestions(newSkill.getQuestions());
            existingSkill.setQuiz(newSkill.getQuiz());
            return skillRepository.save(existingSkill);
        }
        else
            return null;
    }

    public String deleteSkill(int id){
        if (skillRepository.findById(id).isPresent()){
            skillRepository.deleteById(id);
            return "Skill Deleted Successfully.";
        }
        else
            return "Skill Category Failed !!!.";

    }
}
