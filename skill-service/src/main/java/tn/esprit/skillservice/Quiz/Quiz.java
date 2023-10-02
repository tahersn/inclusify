package tn.esprit.skillservice.Quiz;

import tn.esprit.skillservice.Question.Question;
import tn.esprit.skillservice.Skill.Skill;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Quiz implements Serializable {

    private static final long serialVersionUID = 223654759L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Skill skill;

    @ManyToMany()
    @JoinTable(
            name = "quiz_question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;

    private Date dateCreated;

    private int score;
    private boolean isSuccessful;

    public Quiz() {
    }

    public Quiz(Long id, Skill skill, List<Question> questions, Date dateCreated, int score, boolean isSuccessful) {
        this.id = id;
        this.skill = skill;
        this.questions = questions;
        this.dateCreated = dateCreated;
        this.score = score;
        this.isSuccessful = isSuccessful;
    }

    public Quiz(Skill skill, List<Question> questions, Date dateCreated, int score, boolean isSuccessful) {
        this.skill = skill;
        this.questions = questions;
        this.dateCreated = dateCreated;
        this.score = score;
        this.isSuccessful = isSuccessful;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
