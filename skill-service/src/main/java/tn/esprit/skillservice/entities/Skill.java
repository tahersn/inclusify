package tn.esprit.skillservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tn.esprit.skillservice.entities.Question;
import tn.esprit.skillservice.entities.Quiz;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Skill implements Serializable {
    private static final long serialVersionUID = 223654789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String name;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Quiz> quizzes;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public Skill(int id) {
        this.id = id;
    }
    public Skill(String name) {
        this.name = name;
    }
}