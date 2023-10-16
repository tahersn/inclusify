package tn.esprit.eventservice.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CategoryEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    // getters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //setters

    public void setName(String name) {
        this.name = name;
    }

    //Constructors

    public CategoryEvent() {
    }

    public CategoryEvent(String name) {
        this.name = name;
    }
}
