package tn.esprit.jobservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.esprit.jobservice.Job.Job;
import tn.esprit.jobservice.Job.JobRepository;

@SpringBootApplication
public class JobServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobServiceApplication.class, args);
	}
	@Autowired
	private JobRepository jobRepository;

	@Bean
	ApplicationRunner init(){
		return args -> {
			jobRepository.save(new Job("Java Developer","Java Developer","Full Time","1000-2000","Tunis","Esprit",null));
			jobRepository.save(new Job("Java Developer","Java Developer","Full Time","1000-2000","Tunis","Esprit",null));

			jobRepository.findAll().forEach(System.out::println);
		};
	}
}


