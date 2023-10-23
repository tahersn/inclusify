package tn.esprit.feedservice.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;
import tn.esprit.feedservice.entities.*;

import java.util.List;

/**
 * @author Jozef
 */
@Repository
public interface IPostRepository extends CrudRepository<Post,Integer> {

    @Query("select p from Post p where p.userId = ?1")
    List<Post> getPostsByUser(String userId);
}
