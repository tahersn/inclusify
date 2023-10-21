package tn.esprit.marketplaceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.marketplaceservice.entities.Category;
import tn.esprit.marketplaceservice.services.CategoryService;
import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {
    private String title="Hello, I'm the Marketplace/Category Microservice";
    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println(title);
        return  title;
    }

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategory(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") int id,
                                                   @RequestBody Category category){
        return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }
}
