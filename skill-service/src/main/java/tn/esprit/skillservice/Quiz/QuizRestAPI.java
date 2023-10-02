package tn.esprit.skillservice.Quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizRestAPI {

    @Autowired
    private QuizService quizService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return new ResponseEntity<>(quizService.addQuiz(quiz), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
