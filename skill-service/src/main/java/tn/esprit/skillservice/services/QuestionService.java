package tn.esprit.skillservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.skillservice.entities.Question;
import tn.esprit.skillservice.entities.Skill;
import tn.esprit.skillservice.repositories.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestion(int id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(int id, Question newQuestion) {
        if (questionRepository.findById(id).isPresent()) {
            Question existingQuestion = questionRepository.findById(id).get();
            existingQuestion.setDescription(newQuestion.getDescription());
            existingQuestion.setSkill(newQuestion.getSkill());
            existingQuestion.setAnswers(newQuestion.getAnswers());
            return questionRepository.save(existingQuestion);
        } else
            return null;
    }

    public String deleteQuestion(int id) {
        if (questionRepository.findById(id).isPresent()) {
            questionRepository.deleteById(id);
            return "Question Deleted Successfully.";
        } else
            return "Question Category Failed !!!.";

    }
}
