package tn.esprit.jobservice.Job;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT job FROM Job job WHERE job.Title=:title")
    public Page<Job> JobByTitle(@Param("title") String t , Pageable pageable);
}
