package tn.esprit.jobservice.JobApplication;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tn.esprit.jobservice.Job.Job;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity

public class JobApplication implements Serializable {
    private static final long serialVersionUID = 123654789;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String motivation ;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "user_id")
    private String user;



    @Column(name = "cv_path", nullable = false)
    private String cvPath; // Store the file path as a string

    private ApplicationStatus applicationStatus ;

    public JobApplication(String motivation, Job job, String user, String cvPath, ApplicationStatus applicationStatus) {
        this.motivation = motivation;
        this.job = job;
        this.user = user;
        this.cvPath = cvPath;
        this.applicationStatus = applicationStatus;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }



}
