package tn.esprit.skillservice.entities;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String text;
    private boolean isCorrect;

    @ManyToOne
    private Question question;

}