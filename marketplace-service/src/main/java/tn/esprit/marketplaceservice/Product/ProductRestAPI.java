package tn.esprit.marketplaceservice.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Product")
public class ProductRestAPI {
    private String title="Hello, I'm the Marketplace/Product Microservice";
    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println(title);
        return  title;
    }

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
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
