package tn.esprit.jobservice.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Long, Job> {
}
