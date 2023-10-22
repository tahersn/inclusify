package tn.esprit.marketplaceservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category implements Serializable {
    private static final long serialVersionUID = 795450928237931571L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String name,description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;

    public Category(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public Category(String name, String description, List<Product> products) {
        super();
        this.name = name;
        this.description = description;
        this.products = products;
    }
}
