package tn.esprit.feedservice.Configuration;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springdoc.core.*;
import org.springframework.context.annotation.*;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(infoAPI());
    }

    public Info infoAPI() {
        return new Info().title("Inclusify").description("MSA").contact(contactAPI());
    }

    public Contact contactAPI() {
        return new Contact().name("youssef").email("youssef.almia@esprit.tn").url("https://www.linkedin.com/in/youssef-almia-193817183/");
    }

    @Bean
    public GroupedOpenApi productPublicApi() {
        return GroupedOpenApi.builder()
                .group("post")
                .pathsToMatch("/posts/**")
                .pathsToExclude("**")
                .build();

    }



}
