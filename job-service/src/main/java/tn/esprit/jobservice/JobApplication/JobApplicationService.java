package tn.esprit.jobservice.JobApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {
    @Autowired
    private JobApplicationRepo jobApplicationRepo;

    public JobApplication addJobApplication(JobApplication jobApplication){
        return jobApplicationRepo.save(jobApplication);
    }

    public String deleteJobApplication(Long id){
        if(jobApplicationRepo.findById(id).isPresent()){
            jobApplicationRepo.deleteById(id);
            return "JobApplication deleted successfully";
        }
        else{
            return "JobApplication not found";
        }
    }

    public JobApplication updateJobApplication (Long id , JobApplication jobApplication){
        if(jobApplicationRepo.findById(id).isPresent()){
            JobApplication existingJobApplication = jobApplicationRepo.findById(id).get();
            existingJobApplication.setMotivation(jobApplication.getMotivation());
            return jobApplicationRepo.save(jobApplication);
        }
        else{
            return null;
        }

    }

}
