package tn.esprit.jobservice.Job;
import tn.esprit.jobservice.JobApplication.JobApplication;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;


@Entity
public class Job implements Serializable{
    private static final long serialVersionUID = 123654789L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String Title ;
    private String Description ;
    private String Type ;
    private Float SalaryRange ;
    private String Address ;
    private String Company ;


    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<JobApplication> jobApplications;

    public Job() {
        super();
    }

    public Job( String title, String description, String type, Float salaryRange, String address, String company, List<JobApplication> jobApplications) {
        super() ;
        Title = title;
        Description = description;
        Type = type;
        SalaryRange = salaryRange;
        Address = address;
        Company = company;
        this.jobApplications = jobApplications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Float getSalaryRange() {
        return SalaryRange;
    }

    public void setSalaryRange(Float salaryRange) {
        SalaryRange = salaryRange;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }
    public List<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(List<JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }

}
