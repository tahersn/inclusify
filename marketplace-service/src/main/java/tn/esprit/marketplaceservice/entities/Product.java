package tn.esprit.marketplaceservice.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tn.esprit.marketplaceservice.entities.Category;
import tn.esprit.marketplaceservice.model.User;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {
    private static final long serialVersionUID = 795450928237931551L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String name,description,image;
    private int quantity;
    private double price;
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
    @Transient
    private User publisher;
    private String user_id;
    @ManyToOne
    private Category category;

    public Product(String name, String description, String image, int quantity, double price, Category category, String user_id) {
        super();
        this.name = name;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.user_id = user_id;
    }
}
