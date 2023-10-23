package tn.esprit.marketplaceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.marketplaceservice.entities.Category;
import tn.esprit.marketplaceservice.entities.Product;
import tn.esprit.marketplaceservice.feign.UserRestFeignClientService;
import tn.esprit.marketplaceservice.model.User;
import tn.esprit.marketplaceservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private String title="Hello, I'm the Marketplace/Product Microservice";

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRestFeignClientService userFeign;

    @GetMapping(value = "/u", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getU() {
        return userFeign.findAll();
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }
    @GetMapping(value = "/getByUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductsByUser(@PathVariable(value = "id") String id) {
        return productService.getProductsByUser(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") int id,
                                                   @RequestBody Product product){
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
