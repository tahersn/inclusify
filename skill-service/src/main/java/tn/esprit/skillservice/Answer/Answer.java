package tn.esprit.skillservice.Answer;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import tn.esprit.skillservice.Question.Question;


@Entity
public class Answer implements Serializable {
    private static final long serialVersionUID = 213654789L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String text;
    private boolean isCorrect;

    @ManyToOne
    private Question question;

    public Answer() {
        super();
    }

    public Answer(Long id, String text, boolean isCorrect, Question question) {
        super();
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    public Answer(String text, boolean isCorrect, Question question) {
        super();
        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Question getQuestion() { return question; }

    public void setQuestion(Question question) { this.question = question; }
}