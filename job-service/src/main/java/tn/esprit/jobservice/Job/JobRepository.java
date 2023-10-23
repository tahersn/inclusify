package tn.esprit.jobservice.Job;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT job FROM Job job WHERE job.Title=:title")
    public Page<Job> JobByTitle(@Param("title") String t , Pageable pageable);
    //return the list of jobs by user

    @Query("SELECT job FROM Job job WHERE job.user=:user")
    public List<Job> getJobsByUser(@Param("user") String user);

}
