package tn.esprit.marketplaceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.marketplaceservice.entities.Category;
import tn.esprit.marketplaceservice.entities.Product;
import tn.esprit.marketplaceservice.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product newProduct){
        if (productRepository.findById(id).isPresent()){
            Product existingProduct = productRepository.findById(id).get();
            existingProduct.setName(newProduct.getName());
            existingProduct.setDescription(newProduct.getDescription());
            existingProduct.setImage(newProduct.getImage());
            existingProduct.setQuantity(newProduct.getQuantity());
            existingProduct.setPrice(newProduct.getPrice());
            existingProduct.setCategory(newProduct.getCategory());
            return productRepository.save(existingProduct);
        }
        else
            return null;
    }

    public String deleteProduct(int id){
        if (productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
            return "Product Deleted Successfully.";
        }
        else
            return "Deleting Product Failed !!!.";
    }
}
