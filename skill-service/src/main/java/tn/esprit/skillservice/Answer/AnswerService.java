package tn.esprit.skillservice.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public Answer addAnswer(Answer answer){
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(int id, Answer newAnswer){
        if (answerRepository.findById(id).isPresent()){
            Answer existingAnswer = answerRepository.findById(id).get();
            existingAnswer.setText(newAnswer.getText());
            existingAnswer.setCorrect(newAnswer.isCorrect());
            existingAnswer.setQuestion(newAnswer.getQuestion());
            return answerRepository.save(existingAnswer);
        }
        else
            return null;
    }

    public String deleteAnswer(int id){
        if (answerRepository.findById(id).isPresent()){
            answerRepository.deleteById(id);
            return "Answer Deleted Successfully.";
        }
        else
            return "Answer Category Failed !!!.";

    }
}
