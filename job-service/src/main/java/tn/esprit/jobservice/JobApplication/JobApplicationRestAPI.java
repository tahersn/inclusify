package tn.esprit.jobservice.JobApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobApplication")
public class JobApplicationRestAPI {
    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping( consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<JobApplication> addJobApplication(JobApplication jobApplication){
        return new ResponseEntity<>( jobApplicationService.addJobApplication(jobApplication), HttpStatus.OK);
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
}
