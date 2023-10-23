package tn.esprit.jobservice.JobApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobApplicationRepo extends JpaRepository<JobApplication , Long> {
    @Query("SELECT J FROM JobApplication J WHERE J.job.id=:jobId")
    public List<JobApplication> getJobApplicationsByJobId(Long jobId);


    //return the list of job applications for a specific job

}
