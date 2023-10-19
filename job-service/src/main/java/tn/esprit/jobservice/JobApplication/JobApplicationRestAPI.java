package tn.esprit.jobservice.JobApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.jobservice.Job.Job;
import tn.esprit.jobservice.Job.JobService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/jobApplication")
public class JobApplicationRestAPI {
    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private JobService jobService;

    @PostMapping( consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<JobApplication> createJobApplication(
            @RequestParam("cvFile") MultipartFile cvFile,
            @RequestParam("motivation") String motivation,
            @RequestParam("jobId") Long jobId,
            @RequestParam("user") String user,
            @RequestParam("applicationStatus") ApplicationStatus applicationStatus) {

        try {
            // Create a new JobApplication entity
            JobApplication jobApplication = new JobApplication(cvFile.getOriginalFilename(), jobService.getJob(jobId), user, motivation, applicationStatus);
            jobApplication.setMotivation(motivation);

            // Set the CV path in the JobApplication entity
            String cvFilePath = "c:/inclusify-files/" + cvFile.getOriginalFilename();
            jobApplication.setCvPath(cvFilePath);


            // Set the job association and application status
            //Job job = jobService.getJob(jobId);
            jobApplication.setJob(jobService.getJob(jobId));
            jobApplication.setUser(user);
            jobApplication.setApplicationStatus(applicationStatus);

            // Call the service method to create the job application
            JobApplication createdJobApplication = jobApplicationService.addJobApplication(jobApplication, cvFile);

            // Return the created job application as a response
            return ResponseEntity.status(HttpStatus.CREATED).body(createdJobApplication);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<JobApplication> updateJobApplication(@PathVariable("id") Long id , @RequestBody JobApplication jobApplication){
        return new ResponseEntity<>(jobApplicationService.updateJobApplication(id,jobApplication), HttpStatus.OK);
    }
    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteJobApplication(@PathVariable("id") Long id){
        return new ResponseEntity<>(jobApplicationService.deleteJobApplication(id), HttpStatus.OK);
    }
    @GetMapping(value="/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<JobApplication> getJobApplication(@PathVariable("id") Long id){
        return new ResponseEntity<>(jobApplicationService.getJobApplication(id), HttpStatus.OK);
    }

    @GetMapping(value = "/byJobId/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<JobApplication>> getJobApplicationsByJobId(@PathVariable("jobId") Long jobId) {
        List<JobApplication> jobApplications = jobApplicationService.getJobApplicationsByJobId(jobId);
        return new ResponseEntity<>(jobApplications, HttpStatus.OK);
    }

}
