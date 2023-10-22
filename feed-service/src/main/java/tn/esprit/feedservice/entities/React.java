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
public class React {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private User user;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String reactType;

}
