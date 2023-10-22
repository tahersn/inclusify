package tn.esprit.marketplaceservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
@EnableFeignClients
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
			productService.addProduct(new Product("IPhone X", "Powerful phone for all your needs.", "https://www.costco.co.uk/medias/sys_master/images/h37/hc3/119433914056734.jpg",1,999.99,category,"652e9c0148ab2146dc2c51f2"));

			Category fashionCategory = categoryService.addCategory(new Category("Fashion & Apparel", "Stay stylish with our latest fashion collections. Discover trendy clothing, accessories, and footwear, curated to enhance your wardrobe and express your unique style."));
			productService.addProduct(new Product("Dress", "Elegant dress for special occasions.", "https://1861.ca/cdn/shop/files/euphea-green-ES-1_400x.jpg", 5, 79.99, fashionCategory, "652e9c0148ab2146dc2c51f2"));

			Category homeCategory = categoryService.addCategory(new Category("Home & Living", "Transform your living space into a haven of comfort and aesthetics. Find a wide range of furniture, decor, kitchen essentials, and home accessories that blend functionality with elegance."));
			productService.addProduct(new Product("Sofa", "Comfortable sofa for your living room.", "https://www.housingunits.co.uk/media/catalog/product/cache/60968cec045f20fb06ab5f7720001507/b/3/b3b902eece620811430206db7d3ac32c.jpg", 2, 499.99, homeCategory, "652e9c0148ab2146dc2c51f2"));

			Category healthCategory = categoryService.addCategory(new Category("Health & Wellness", "Prioritize your well-being with our health and wellness products. From vitamins and supplements to fitness equipment and self-care essentials, we offer everything you need to lead a healthy lifestyle."));
			productService.addProduct(new Product("Fitness Tracker", "Track your fitness goals with this advanced fitness tracker.", "https://img.kleinanzeigen.de/api/v1/prod-ads/images/0a/0a535fd9-7870-4ca3-87e3-3a9a3e03e4b0?rule=$_20.JPG", 10, 89.99, healthCategory, "652e9c0148ab2146dc2c51f2"));

			Category booksCategory = categoryService.addCategory(new Category("Books & Stationery", "Immerse yourself in the world of literature and creativity. Explore an extensive collection of books, stationery items, and art supplies that inspire learning, creativity, and productivity."));
			productService.addProduct(new Product("Notebook Set", "High-quality notebook set for all your writing needs.", "https://images.squarespace-cdn.com/content/v1/5349ba13e4b095a3fb0ba65c/1612967928091-9WE3FR9P6J89T08O27GA/Travelers-Notebook-Write-Notepads-Commonplace.jpeg", 20, 19.99, booksCategory, "652e9c0148ab2146dc2c51f2"));
		};
	}

//	@Bean
//	public static WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedOrigins("http://localhost:5000")
//						.allowedMethods("GET", "POST", "PUT", "DELETE")
//						.allowedHeaders("*");
//			}
//		};
//	}
}
