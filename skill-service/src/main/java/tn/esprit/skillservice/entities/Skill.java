package tn.esprit.skillservice.entities;

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

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}