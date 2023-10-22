package tn.esprit.gatewayservice.security;

import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.*;

import org.springframework.core.*;
import org.springframework.http.*;
import org.springframework.security.config.web.server.*;
import org.springframework.security.oauth2.client.oidc.web.server.logout.*;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.web.server.*;
import org.springframework.security.web.server.authentication.logout.*;
import org.springframework.web.cors.*;
import org.springframework.web.cors.reactive.*;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.*;
import org.springframework.web.util.pattern.*;

import java.util.*;

/**
 * @author Jozef
 */

@Configuration
public class ResourceServerSecurityConfig {
    @Bean
    public SecurityWebFilterChain configureResourceServer(ServerHttpSecurity httpSecurity, ServerLogoutSuccessHandler handler) throws Exception {

        httpSecurity.

                authorizeExchange().pathMatchers("/actuator/health/**",
                        "/nodejs-service/api-docs/**",
                        "/skill-service/**",
                        "/h2-console",
                        "/login**",
                        "/nodejs-service/users/**",
                        "api-docs/**",
                        "/feed-service/swagger-ui.html").permitAll()
                .pathMatchers(HttpMethod.OPTIONS,"/feed-service/**","/event-service/event/**").permitAll()
                .and()
                .authorizeExchange().anyExchange().authenticated()
                .and()
                .oauth2Login()
                .and()
                .csrf().disable()
                .cors().disable()
                .logout().logoutSuccessHandler(handler).and().oauth2ResourceServer().jwt();


        return httpSecurity.build();
    }

    @Bean
    public ServerLogoutSuccessHandler keycloakLogOutSuccessfullHandler(ReactiveClientRegistrationRepository reactiveClientRegistrationRepository){
        OidcClientInitiatedServerLogoutSuccessHandler oidcClientInitiatedServerLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(reactiveClientRegistrationRepository);

        oidcClientInitiatedServerLogoutSuccessHandler.setPostLogoutRedirectUri("/logout");

        return oidcClientInitiatedServerLogoutSuccessHandler;
    }

    @Bean
    public CorsWebFilter corsFilter() {
        return new CorsWebFilter(corsConfigurationSource());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:5000")); // Allow any origin for preflight requests

        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.GET);
        config.setMaxAge(3600L);

        System.out.println(config.getAllowedOrigins());
        source.registerCorsConfiguration("/**", config);
        return source;
    }


}
