package tn.esprit.skillservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.skillservice.entities.Quiz;
import tn.esprit.skillservice.entities.Skill;
import tn.esprit.skillservice.models.SkillListItem;
import tn.esprit.skillservice.repositories.SkillRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private QuizService quizService;

    public List<SkillListItem> getSkills(){
        List<Skill> skills=skillRepository.findAll();
        List<SkillListItem> res= new ArrayList<>();
        for (Skill skill: skills) {
            SkillListItem item= new SkillListItem();
            item.setSkill(skill);
            res.add(item);
        }
        return res;
    }

    public List<SkillListItem> getSkillItems(String userId){
        List<Skill> skills=skillRepository.getPlayableSkills();
        List<Integer> succSkillsId= quizService.getQuizSuccessful(userId);
        List<SkillListItem> res= new ArrayList<>();
        for (Skill skill: skills) {
            SkillListItem item= new SkillListItem();
            item.setSkill(skill);
            if (succSkillsId.contains(skill.getId()))
                item.setDone(true);
            else item.setDone(false);
            res.add(item);
        }
        return res;
    }

    public Skill getSkill(int id){
        return skillRepository.findById(id).orElse(null);
    }

    public Skill addSkill(Skill skill){
        return skillRepository.save(skill);
    }

    public Skill updateSkill(int id, Skill newSkill){
        if (skillRepository.findById(id).isPresent()){
            Skill existingSkill = skillRepository.findById(id).get();
            existingSkill.setName(newSkill.getName());
            existingSkill.setQuestions(newSkill.getQuestions());
            existingSkill.setQuizzes(newSkill.getQuizzes());
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
