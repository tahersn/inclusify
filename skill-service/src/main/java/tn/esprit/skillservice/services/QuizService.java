package tn.esprit.skillservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.esprit.skillservice.entities.Answer;
import tn.esprit.skillservice.entities.Question;
import tn.esprit.skillservice.entities.Quiz;
import tn.esprit.skillservice.entities.Skill;
import tn.esprit.skillservice.models.QuizAnswer;
import tn.esprit.skillservice.repositories.QuizRepository;

import java.util.List;
import java.util.Map;


@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionService questionService;

    public Quiz getQuizById(int quizId){return quizRepository.findById(quizId).orElse(null);}

    public List<Integer> getQuizSuccessful(String userId){
        return quizRepository.getQuizByUser(userId);
    }

    public Quiz generateQuizBySkill(Skill skill){
        Quiz quiz = new Quiz();
        quiz.setSkill(skill);
        quiz.setScore(0);
        quiz.setSuccessful(false);
        quiz.setUser_id("userId");
        quiz.setQuestions(questionService.getQuestionsBySkill(skill.getId()));
        return quizRepository.save(quiz);
    }

    public Quiz submitQuiz(int quizId, QuizAnswer answers){

        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        float total = 0;
        float score = 0;
        if(answers.getData() != null) {
            Map<Integer, Boolean> data = answers.getData();
            for (Question question : quiz.getQuestions()) {
                total += question.getAnswers().size();
                for (Answer answer : question.getAnswers()) {
                    if (
                            (data.containsKey(answer.getId()) && data.get(answer.getId()) && answer.isCorrect()) ||
                                    (!data.containsKey(answer.getId()) && !answer.isCorrect()) ||
                                    (data.containsKey(answer.getId()) && !data.get(answer.getId()) && !answer.isCorrect())
                    )
                        score++;
                }
            }
        }else {
            for (Question question : quiz.getQuestions()) {
                total += question.getAnswers().size();
            }
        }
        quiz.setScore((score/total)*100);
        if (quiz.getScore() >=60)
            quiz.setSuccessful(true);
        quizRepository.save(quiz);

        return quiz;
    }

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
