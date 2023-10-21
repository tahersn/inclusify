package tn.esprit.skillservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.skillservice.entities.Quiz;
import tn.esprit.skillservice.repositories.QuizRepository;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    
    public Quiz addQuiz(Quiz Quiz){
        return quizRepository.save(Quiz);
    }

    public Quiz updateQuiz(int id, Quiz newQuiz){
        if (quizRepository.findById(id).isPresent()){
            Quiz existingQuiz = quizRepository.findById(id).get();
            existingQuiz.setSkill(newQuiz.getSkill());
            existingQuiz.setQuestions(newQuiz.getQuestions());
            existingQuiz.setScore(newQuiz.getScore());
            existingQuiz.setSuccessful(newQuiz.isSuccessful());
            return quizRepository.save(existingQuiz);
        }
        else
            return null;
    }

    public String deleteQuiz(int id){
        if (quizRepository.findById(id).isPresent()){
            quizRepository.deleteById(id);
            return "Quiz Deleted Successfully.";
        }
        else
            return "Quiz Category Failed !!!.";

    }
}
