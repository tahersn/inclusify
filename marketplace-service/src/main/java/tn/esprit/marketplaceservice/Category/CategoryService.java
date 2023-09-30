package tn.esprit.marketplaceservice.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(int id, Category newCategory){
        if (categoryRepository.findById(id).isPresent()){
                Category existingCategory = categoryRepository.findById(id).get();
                existingCategory.setName(newCategory.getName());
                existingCategory.setDescription(newCategory.getDescription());
                existingCategory.setProducts(newCategory.getProducts());
                return categoryRepository.save(existingCategory);
        }
        else
            return null;
    }

    public String deleteCategory(int id){
        if (categoryRepository.findById(id).isPresent()){
            categoryRepository.deleteById(id);
            return "Category Deleted Successfully.";
        }
        else
            return "Deleting Category Failed !!!.";

    }
}
