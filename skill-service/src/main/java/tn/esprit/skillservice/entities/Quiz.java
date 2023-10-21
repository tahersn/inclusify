package tn.esprit.skillservice.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tn.esprit.skillservice.models.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Quiz implements Serializable {

    private static final long serialVersionUID = 223654759L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    private Skill skill;

    @ManyToMany()
    @JoinTable(
            name = "quiz_question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;

    private int score;
    private boolean isSuccessful;
    private String user_id;

    @Transient
    private User user;


    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public Quiz(Skill skill, int score, boolean isSuccessful, String user_id) {
        this.skill = skill;
        this.score = score;
        this.isSuccessful = isSuccessful;
        this.user_id = user_id;
    }
}
