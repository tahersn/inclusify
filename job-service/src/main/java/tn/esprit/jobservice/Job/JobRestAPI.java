package tn.esprit.jobservice.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobRestAPI {
    @Autowired
    private JobService jobService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
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
    @GetMapping("/byTitle")
    public ResponseEntity<Page<Job>> getJobsByTitle(@RequestParam("title") String title, Pageable pageable) {
        Page<Job> jobs = jobService.getJobsByTitle(title, pageable);
        if (!jobs.isEmpty()) {
            return ResponseEntity.ok(jobs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<Iterable<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        if (!jobs.isEmpty()) {
            return ResponseEntity.ok(jobs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/byUser/{user}")
    public List<Job> getJobsByUser(@PathVariable("user") String user) {
        List<Job> jobs = jobService.getJobsByUserId(user);
        if (!jobs.isEmpty()) {
            return jobs;
        } else {
            return null;
        }
    }

}
