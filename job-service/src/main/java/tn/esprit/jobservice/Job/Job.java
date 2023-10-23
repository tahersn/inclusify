package tn.esprit.jobservice.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.jobservice.JobApplication.JobApplication;
import tn.esprit.jobservice.model.User;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
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
    private String SalaryRange ;
    private String Address ;
    private String Company ;

    @Column(name = "user_id")
    private String user;

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private List<JobApplication> jobApplications;

    public Job() {
        super();
    }

    public Job( String title, String description, String type, String salaryRange, String address, String company,String user, List<JobApplication> jobApplications) {
        super() ;
        this.Title = title;
        this.Description = description;
        this.Type = type;
        this.SalaryRange = salaryRange;
        this.Address = address;
        this.Company = company;
        this.jobApplications = jobApplications;
        this.user = user;


    }

    public Job(Long jobId) {
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

    public String getSalaryRange() {
        return SalaryRange;
    }

    public void setSalaryRange(String salaryRange) {
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
    public String getUser() {

        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
