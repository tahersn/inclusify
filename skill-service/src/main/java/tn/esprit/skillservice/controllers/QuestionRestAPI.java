package tn.esprit.skillservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.skillservice.services.QuestionService;
import tn.esprit.skillservice.entities.Question;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionRestAPI {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/bySkill/{skillId}")
    public List<Question> getQuestionsBySkill(@PathVariable(value = "skillId") int skillId) {
        return questionService.getQuestionsBySkill(skillId);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    @GetMapping(value = "/byId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> getQuestion(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(questionService.getQuestion(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Question> updateQuestion(@PathVariable(value = "id") int id,
                                                 @RequestBody Question question){
        return new ResponseEntity<>(questionService.updateQuestion(id, question), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteQuestion(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(questionService.deleteQuestion(id), HttpStatus.OK);
    }
}
