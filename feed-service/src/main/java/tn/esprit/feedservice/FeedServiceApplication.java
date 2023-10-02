package tn.esprit.feedservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.*;
import org.springframework.cloud.client.discovery.*;
import org.springframework.cloud.context.config.annotation.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.*;
import tn.esprit.feedservice.Configuration.*;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
@EnableFeignClients
@EnableDiscoveryClient // optional ?
@RefreshScope
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class FeedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedServiceApplication.class, args);
	}

}
