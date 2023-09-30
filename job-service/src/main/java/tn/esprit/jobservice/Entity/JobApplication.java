package tn.esprit.jobservice.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Entity
public class JobApplication implements Serializable {
    private static final long serialVersionUID = 123654789;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

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

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    private String motivation ;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private ApplicationStatus applicationStatus ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
