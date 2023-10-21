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

        httpSecurity.authorizeExchange().pathMatchers("/actuator/health/**","/nodejs-service/api-docs/**","/login**","/nodejs-service/users/**").permitAll()
                .and()
                .authorizeExchange().anyExchange().authenticated()
                .and()
                .oauth2Login()
                .and()
                .csrf().disable()
                .cors().disable()
                .logout().logoutSuccessHandler(handler);

        return httpSecurity.build();
    }

    @Bean
    public ServerLogoutSuccessHandler keycloakLogOutSuccessfullHandler(ReactiveClientRegistrationRepository reactiveClientRegistrationRepository){
        OidcClientInitiatedServerLogoutSuccessHandler oidcClientInitiatedServerLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(reactiveClientRegistrationRepository);

        oidcClientInitiatedServerLogoutSuccessHandler.setPostLogoutRedirectUri("/logout");

        return oidcClientInitiatedServerLogoutSuccessHandler;
    }

//    @Bean
//    public CorsWebFilter corsWebFilter() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.addAllowedOrigin("*");
//        corsConfig.addAllowedHeader("*");
//        corsConfig.addAllowedMethod("*");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return new CorsWebFilter(source);
//    }

    @Bean
    public CorsWebFilter corsFilter() {
        return new CorsWebFilter(corsConfigurationSource());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedOrigins(Collections.singletonList("*")); // Allow any origin for preflight requests

        config.addAllowedOrigin("*");
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
