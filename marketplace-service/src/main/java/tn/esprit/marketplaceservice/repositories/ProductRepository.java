package tn.esprit.marketplaceservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.marketplaceservice.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.user_id=?1")
    List<Product> getProductByUser(String userId);
}
