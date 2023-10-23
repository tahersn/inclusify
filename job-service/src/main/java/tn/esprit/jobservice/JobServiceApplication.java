package tn.esprit.jobservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tn.esprit.jobservice.Job.Job;
import tn.esprit.jobservice.Job.JobRepository;
import tn.esprit.jobservice.JobApplication.JobApplication;
import tn.esprit.jobservice.JobApplication.JobApplicationRepo;
import tn.esprit.jobservice.feign.UserRestService;
import tn.esprit.jobservice.model.User;


import static tn.esprit.jobservice.JobApplication.ApplicationStatus.PENDING;
import static tn.esprit.jobservice.JobApplication.ApplicationStatus.REJECTED;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
@EnableFeignClients
@EnableDiscoveryClient // optional ?
@RefreshScope
public class JobServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobServiceApplication.class, args);
	}
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private JobApplicationRepo jobApplicationRepo;
	private final UserRestService userRestService;

	public JobServiceApplication(UserRestService userRestService) {
		this.userRestService = userRestService;
	}

	@Bean
	ApplicationRunner init(){
		return args -> {
			//User u = userRestService.findById("652e9c0148ab2146dc2c51f2");
			jobRepository.save(new Job("Java Developer","Java Developer","Full Time","1000-2000","Tunis","Esprit","652e9c0148ab2146dc2c51f2",null));
			jobRepository.save(new Job("Java Developer","Java Developer","Full Time","1000-2000","Tunis","Esprit","652e9c0148ab2146dc2c51f2",null));
			jobRepository.findAll().forEach(System.out::println);
			//System.out.println(u);
			jobApplicationRepo.save(new JobApplication("motivation",jobRepository.findById(Long.valueOf(1)).get(),"652e9c0148ab2146dc2c51f2","C:/inclusify_resumes/cv.pdf",PENDING));
		};
	}
	//@Bean
	//public static WebMvcConfigurer corsConfigurer() {
		//return new WebMvcConfigurer() {
			//@Override
			//public void addCorsMappings(CorsRegistry registry) {
			//	registry.addMapping("/**")
			//			.allowedOrigins("http://localhost:5000")
			//			.allowedMethods("GET", "POST", "PUT", "DELETE")
			//			.allowedHeaders("*");
			//}
		//};
	//}
}