package tn.esprit.marketplaceservice.Category;

import tn.esprit.marketplaceservice.Product.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Category implements Serializable {
    private static final long serialVersionUID = 795450928237931571L;
    @Id
    @GeneratedValue
    private int id;
    private String name,description;
    @ManyToMany
    Set<Product> products;

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
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

    public Category(String name, String description, Set<Product> products) {
        super();
        this.name = name;
        this.description = description;
        this.products = products;
    }
}
