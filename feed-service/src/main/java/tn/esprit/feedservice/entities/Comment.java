package tn.esprit.feedservice.entities;

import lombok.*;
import tn.esprit.feedservice.model.*;

import javax.persistence.*;

/**
 * @author Jozef
 */
@Entity
@Getter
@Setter @AllArgsConstructor
@NoArgsConstructor @ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String comment;

    @Transient
    private User user;

    private String userId;
}
