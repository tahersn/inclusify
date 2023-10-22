package tn.esprit.feedservice.entities;

import lombok.*;
import org.hibernate.annotations.*;
import tn.esprit.feedservice.enums.*;
import tn.esprit.feedservice.model.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.sql.*;
import java.util.*;

/**
 * @author Jozef
 */
@Entity
@Getter
@Setter @AllArgsConstructor
@NoArgsConstructor @ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    private List<String> images;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    @Enumerated(EnumType.STRING)
    private PostType postTypeEnum = PostType.ANNOUNCEMENT;

    @Transient
    private User user;

    private String userId;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<React> reacts;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
