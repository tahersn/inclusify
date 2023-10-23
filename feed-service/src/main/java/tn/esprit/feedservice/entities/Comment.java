package tn.esprit.feedservice.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import tn.esprit.feedservice.model.*;

import javax.persistence.*;

/**
 * @author Jozef
 */
@Entity
@Getter
@Setter @AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    @Column(nullable = false)
    private String comment;

    @Transient
    private User user;

    private String userId;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", post=" + post +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", userId='" + userId + '\'' +
                '}';
    }
}
