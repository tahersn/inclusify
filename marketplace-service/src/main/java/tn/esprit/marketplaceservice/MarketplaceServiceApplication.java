package tn.esprit.marketplaceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.esprit.marketplaceservice.entities.Category;
import tn.esprit.marketplaceservice.repositories.CategoryRepository;
import tn.esprit.marketplaceservice.repositories.ProductRepository;

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
			categoryRepository.save(new Category("Electronics","Explore cutting-edge gadgets and devices, from smartphones to smart home appliances, designed to simplify your life and keep you connected."));
			categoryRepository.save(new Category("Fashion & Apparel","Stay stylish with our latest fashion collections. Discover trendy clothing, accessories, and footwear, curated to enhance your wardrobe and express your unique style."));
			categoryRepository.save(new Category("Home & Living","Transform your living space into a haven of comfort and aesthetics. Find a wide range of furniture, decor, kitchen essentials, and home accessories that blend functionality with elegance."));
			categoryRepository.save(new Category("Health & Wellness","Prioritize your well-being with our health and wellness products. From vitamins and supplements to fitness equipment and self-care essentials, we offer everything you need to lead a healthy lifestyle."));
			categoryRepository.save(new Category("Books & Stationery","Immerse yourself in the world of literature and creativity. Explore an extensive collection of books, stationery items, and art supplies that inspire learning, creativity, and productivity."));
		};
	}
}
