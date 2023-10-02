package tn.esprit.skillservice.Skill;

import tn.esprit.skillservice.Question.Question;
import tn.esprit.skillservice.Quiz.Quiz;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Skill implements Serializable{
    private static final long serialVersionUID = 223654789L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name ;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<Quiz> quiz;

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
        this.quiz = new ArrayList<>();
    }

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
        this.questions = new ArrayList<>();
        this.quiz = new ArrayList<>();
    }


    public Skill(Long id, String name, List<Question> questions, List<Quiz> quiz) {
        this.id = id;
        this.name = name;
        this.questions = questions;
        this.quiz = quiz;
    }

    public Skill(String name, List<Question> questions, List<Quiz> quiz) {
        this.name = name;
        this.questions = questions;
        this.quiz = quiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }
}