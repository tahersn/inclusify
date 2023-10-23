package tn.esprit.jobservice.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Job addJob(Job job){
        return jobRepository.save(job);
    }

    public String deleteJob(Long id){
        if(jobRepository.findById(id).isPresent()){
            jobRepository.deleteById(id);
            return "Job deleted successfully";
        }
        else{
            return "Job not found";
        }
    }
    public Job updateJob (Long id , Job job){
        if(jobRepository.findById(id).isPresent()){
            Job existingJob = jobRepository.findById(id).get();
        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setType(job.getType());
        existingJob.setSalaryRange(job.getSalaryRange());
        existingJob.setAddress(job.getAddress());
        existingJob.setCompany(job.getCompany());
        existingJob.setJobApplications(job.getJobApplications());
            return jobRepository.save(job);
    }
        else{
            return null;
        }

    }
    public Job getJob(Long id){
        if(jobRepository.findById(id).isPresent()){
            return jobRepository.findById(id).get();
        }
        else{
            return null;
        }
    }
    public Page<Job> getJobsByTitle(String title, Pageable pageable) {
        return jobRepository.JobByTitle(title, pageable);
    }

    //get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobsByUserId(String userId) {
        return jobRepository.getJobsByUser(userId);
    }

}
