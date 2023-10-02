package tn.esprit.skillservice.Quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            existingQuiz.setDateCreated(newQuiz.getDateCreated());
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
