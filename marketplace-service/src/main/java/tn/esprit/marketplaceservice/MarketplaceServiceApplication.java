package tn.esprit.marketplaceservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tn.esprit.marketplaceservice.entities.Category;
import tn.esprit.marketplaceservice.entities.Product;
import tn.esprit.marketplaceservice.repositories.CategoryRepository;
import tn.esprit.marketplaceservice.repositories.ProductRepository;
import tn.esprit.marketplaceservice.services.CategoryService;
import tn.esprit.marketplaceservice.services.ProductService;

@SpringBootApplication
public class MarketplaceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceServiceApplication.class, args);
	}
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@Bean
	InitializingBean initProductTable(){
		return ()->{
			Category category= categoryService.addCategory(new Category("Electronics","Explore cutting-edge gadgets and devices, from smartphones to smart home appliances, designed to simplify your life and keep you connected."));
			productService.addProduct(new Product("IPhone X", "Powerful phone for all your needs.", "https://www.costco.co.uk/medias/sys_master/images/h37/hc3/119433914056734.jpg",1,999.99,category,"1"));

			Category fashionCategory = categoryService.addCategory(new Category("Fashion & Apparel", "Stay stylish with our latest fashion collections. Discover trendy clothing, accessories, and footwear, curated to enhance your wardrobe and express your unique style."));
			productService.addProduct(new Product("Dress", "Elegant dress for special occasions.", "https://example.com/dress.jpg", 5, 79.99, fashionCategory, "1"));

			Category homeCategory = categoryService.addCategory(new Category("Home & Living", "Transform your living space into a haven of comfort and aesthetics. Find a wide range of furniture, decor, kitchen essentials, and home accessories that blend functionality with elegance."));
			productService.addProduct(new Product("Sofa", "Comfortable sofa for your living room.", "https://example.com/sofa.jpg", 2, 499.99, homeCategory, "1"));

			Category healthCategory = categoryService.addCategory(new Category("Health & Wellness", "Prioritize your well-being with our health and wellness products. From vitamins and supplements to fitness equipment and self-care essentials, we offer everything you need to lead a healthy lifestyle."));
			productService.addProduct(new Product("Fitness Tracker", "Track your fitness goals with this advanced fitness tracker.", "https://example.com/fitness-tracker.jpg", 10, 89.99, healthCategory, "1"));

			Category booksCategory = categoryService.addCategory(new Category("Books & Stationery", "Immerse yourself in the world of literature and creativity. Explore an extensive collection of books, stationery items, and art supplies that inspire learning, creativity, and productivity."));
			productService.addProduct(new Product("Notebook Set", "High-quality notebook set for all your writing needs.", "https://example.com/notebook-set.jpg", 20, 19.99, booksCategory, "1"));
		};
	}

	@Bean
	public static WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:8888")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
			}
		};
	}
}
