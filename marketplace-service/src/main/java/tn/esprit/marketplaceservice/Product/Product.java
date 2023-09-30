package tn.esprit.marketplaceservice.Product;

import tn.esprit.marketplaceservice.Category.Category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    @ManyToMany
    Set<Category> categories;

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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Product() {
        super();
    }

    public Product(int id, String name, String description, String image, int quantity, float price, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.categories = categories;
    }
}
