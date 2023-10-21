package tn.esprit.marketplaceservice.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.List;

@Entity
public class Category implements Serializable {
    private static final long serialVersionUID = 795450928237931571L;
    @Id
    @GeneratedValue
    private int id;
    private String name,description;
    @OneToMany
    private List<Product> products;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Category() {
        super();
    }

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
