package tn.esprit.marketplaceservice.entities;

import tn.esprit.marketplaceservice.entities.Category;
import tn.esprit.marketplaceservice.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 795450928237931551L;
    @Id
    @GeneratedValue
    private int id;
    private String name,description,image;
    private int quantity;
    private float price;
    @Transient
    private User publisher;
    private int UserId;
    @ManyToOne
    private Category category;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
        super();
    }

    public Product(String name, String description, String image, int quantity, float price, Set<Category> categories) {
        super();
        this.name = name;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }
}
