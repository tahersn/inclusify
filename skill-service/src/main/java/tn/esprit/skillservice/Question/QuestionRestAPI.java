package tn.esprit.skillservice.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionRestAPI {

    @GetMapping("/hello")
    public String sayHello(){
        return "Salem";
    }
    @Autowired
    private QuestionService questionService;

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
