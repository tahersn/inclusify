package tn.esprit.gatewayservice.security;

import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.core.session.*;
import org.springframework.security.web.authentication.session.*;

/**
 * @author Jozef
 */
//@KeycloakConfiguration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {//extends KeycloakWebSecurityConfigurerAdapter {
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(keycloakAuthenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.csrf().disable();
//        http.authorizeHttpRequests().antMatchers("/nodejs-service/api-docs/**").permitAll();
//        http.headers().frameOptions().disable();
//        http.authorizeHttpRequests().anyRequest().authenticated();
//    }
}
