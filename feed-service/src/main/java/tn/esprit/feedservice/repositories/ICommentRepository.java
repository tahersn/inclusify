package tn.esprit.feedservice.repositories;

import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;
import tn.esprit.feedservice.entities.*;

/**
 * @author Jozef
 */
@Repository
public interface ICommentRepository extends CrudRepository<Comment, Long> {
}
