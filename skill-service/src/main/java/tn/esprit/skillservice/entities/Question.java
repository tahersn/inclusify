package tn.esprit.skillservice.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question implements Serializable {
    private static final long serialVersionUID = 223654789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String description;

    @ManyToMany(mappedBy = "questions")
    private List<Quiz> quizzes;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    @ManyToOne
    private Skill skill;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;


    public Question(String description, Skill skill) {
        this.description = description;
        this.skill = skill;
    }
}