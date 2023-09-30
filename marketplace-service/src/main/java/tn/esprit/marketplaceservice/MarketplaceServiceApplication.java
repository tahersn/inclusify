package tn.esprit.marketplaceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.esprit.marketplaceservice.Category.Category;
import tn.esprit.marketplaceservice.Category.CategoryRepository;
import tn.esprit.marketplaceservice.Product.ProductRepository;

@SpringBootApplication
public class MarketplaceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceServiceApplication.class, args);
	}
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Bean
	ApplicationRunner init(){
		return (args) -> {
			categoryRepository.save(new Category("Educational","This is an educational product"));
			categoryRepository.save(new Category("Entertainment","This is an entertainment product"));
		};
	}

}
