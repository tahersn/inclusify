package tn.esprit.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.*;
import org.springframework.cloud.context.config.annotation.*;
import org.springframework.cloud.gateway.discovery.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.reactive.*;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient reactiveDiscoveryClient, DiscoveryLocatorProperties dlp){
		return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient,dlp);
	}
}
