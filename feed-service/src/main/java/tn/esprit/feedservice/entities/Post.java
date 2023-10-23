package tn.esprit.feedservice.entities;

import com.fasterxml.jackson.annotation.*;
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
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    private List<String> images;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comments;

    @Transient
    private User user;

    private String userId;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<React> reacts;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
