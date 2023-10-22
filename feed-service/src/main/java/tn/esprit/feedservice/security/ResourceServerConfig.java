package tn.esprit.feedservice.security;

import org.springframework.context.annotation.*;
import org.springframework.core.convert.converter.*;

import org.springframework.http.*;
//import org.springframework.security.authentication.*;
//import org.springframework.security.config.annotation.method.configuration.*;
//import org.springframework.security.config.annotation.web.builders.*;
//import org.springframework.security.config.annotation.web.configuration.*;
//import org.springframework.security.oauth2.jwt.*;
//import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.web.cors.*;
import org.springframework.web.cors.reactive.*;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.*;


/**
 * @author Jozef
 */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class ResourceServerConfig {//extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/swagger-ui/**", "/v3/api-docs/**","/swagger-resources/*").permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2ResourceServer().jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAthenticationConverter()));
//    }
//
//    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAthenticationConverter() {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RealmRoleConverter());
//
//        return jwtAuthenticationConverter;
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(Collections.singletonList("http://localhost:5000")); // Allow any origin for preflight requests
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.setMaxAge(3600L);
//
//        System.out.println(config.getAllowedOrigins());
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }

}
