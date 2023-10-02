package tn.esprit.jobservice.JobApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

@Service
public class JobApplicationService {
    @Autowired
    private JobApplicationRepo jobApplicationRepo;

    @Transactional
    public JobApplication addJobApplication(JobApplication jobApplication, MultipartFile cvFile) throws IOException {
        // Add any validation logic you need for the CV or motivation.

        // Store the CV file in the specified directory
        String cvFilePath = "c:/inclusify-files/" + cvFile.getOriginalFilename();
        File cvFileOnDisk = new File(cvFilePath);
        cvFile.transferTo(cvFileOnDisk);

        // Set the CV path in the JobApplication entity
        jobApplication.setCvPath(cvFilePath);


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
