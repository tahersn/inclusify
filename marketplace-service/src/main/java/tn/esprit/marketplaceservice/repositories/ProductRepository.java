package tn.esprit.marketplaceservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.marketplaceservice.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
