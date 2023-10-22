package tn.esprit.marketplaceservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.marketplaceservice.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where c.name like :name")
    public Page<Category> categoryByName(@Param("name") String n, Pageable pageable);
}
