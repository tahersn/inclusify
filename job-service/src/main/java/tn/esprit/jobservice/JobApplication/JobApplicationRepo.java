package tn.esprit.jobservice.JobApplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepo extends JpaRepository<JobApplication , Long> {
}
