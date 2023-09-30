package tn.esprit.jobservice.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobRestAPI {
    @Autowired
    private JobService jobService;
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Job> addJob(@RequestBody Job job){
        return new ResponseEntity<>(jobService.addJob(job), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteJob(@PathVariable("id") Long id){
        return new ResponseEntity<>(jobService.deleteJob(id), HttpStatus.OK);
    }
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Job> updateJob(@PathVariable("id") Long id , @RequestBody Job job){
        return new ResponseEntity<>(jobService.updateJob(id,job), HttpStatus.OK);
    }
    @GetMapping(value="/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Job> getJob(@PathVariable("id") Long id){
        return new ResponseEntity<>(jobService.getJob(id), HttpStatus.OK);
    }
}
