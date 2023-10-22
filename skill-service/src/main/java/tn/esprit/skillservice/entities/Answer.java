package tn.esprit.skillservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Answer implements Serializable {
    private static final long serialVersionUID = 213654789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String text;
    private boolean isCorrect;

    @ManyToOne
    @JsonIgnore
    private Question question;

    public Answer(String text, boolean isCorrect, Question question) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }
}