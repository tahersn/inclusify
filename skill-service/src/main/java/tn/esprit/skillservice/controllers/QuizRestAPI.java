package tn.esprit.skillservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.skillservice.entities.Skill;
import tn.esprit.skillservice.models.QuizAnswer;
import tn.esprit.skillservice.services.QuestionService;
import tn.esprit.skillservice.services.QuizService;
import tn.esprit.skillservice.entities.Quiz;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizRestAPI {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/byId/{quizId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz getQuizById(@PathVariable(value = "quizId") int quizId ) {
        return quizService.getQuizById(quizId);
    }

    @PostMapping(value = "/generateQuizBySkill/{skillId}/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz generateQuizBySkill(@PathVariable(value = "skillId") int skillId, @PathVariable(value = "userId") String userId ) {
        return quizService.generateQuizBySkill(new Skill(skillId), userId);
    }

    @PostMapping(value = "/submitQuiz/{quizId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz submitQuiz(@PathVariable(value = "quizId") int quizId, @RequestBody QuizAnswer answers ) {
        return quizService.submitQuiz(quizId, answers);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return new ResponseEntity<>(quizService.addQuiz(quiz), HttpStatus.OK);
    }

    @PutMapping(value = "/byId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Quiz> updateQuiz(@PathVariable(value = "id") int id,
                                                 @RequestBody Quiz quiz){
        return new ResponseEntity<>(quizService.updateQuiz(id, quiz), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteQuiz(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(quizService.deleteQuiz(id), HttpStatus.OK);
    }
}
