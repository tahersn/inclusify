package tn.esprit.eventservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.eventservice.Entity.CategoryEvent;
import tn.esprit.eventservice.Repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<CategoryEvent> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public CategoryEvent createCategory(@RequestBody CategoryEvent category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public CategoryEvent updateCategory(@PathVariable Integer id, @RequestBody CategoryEvent category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        }
        return null; // Return an error or handle as needed
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public CategoryEvent getCategoryById(@PathVariable Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

}
