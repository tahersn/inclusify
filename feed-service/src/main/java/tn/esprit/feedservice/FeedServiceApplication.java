package tn.esprit.feedservice;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.*;
import org.springframework.cloud.client.discovery.*;
import org.springframework.cloud.context.config.annotation.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.*;
import tn.esprit.feedservice.Configuration.*;
import tn.esprit.feedservice.entities.*;
import tn.esprit.feedservice.feign.*;
import tn.esprit.feedservice.model.*;
import tn.esprit.feedservice.repositories.*;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
@EnableFeignClients
@EnableDiscoveryClient // optional ?
@RefreshScope
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class FeedServiceApplication implements CommandLineRunner {
	@Autowired
	IPostRepository postRepository;


//	private final UserRestFeignClientService userRestFeignClientService;
//
//	public FeedServiceApplication(UserRestFeignClientService userRestFeignClientService) {
//		this.userRestFeignClientService = userRestFeignClientService;
//	}

	public static void main(String[] args) {
		SpringApplication.run(FeedServiceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Post post1 = new Post(null,"test post",null,null,null,null,null,null,null, null);
		postRepository.save(post1);



	}

}
