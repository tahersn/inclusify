package tn.esprit.gatewayservice.security;

import org.springframework.context.annotation.*;

import org.springframework.security.config.web.server.*;
import org.springframework.security.oauth2.client.oidc.web.server.logout.*;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.web.server.*;
import org.springframework.security.web.server.authentication.logout.*;

/**
 * @author Jozef
 */

@Configuration
public class ResourceServerSecurityConfig {
    @Bean
    public SecurityWebFilterChain configureResourceServer(ServerHttpSecurity httpSecurity, ServerLogoutSuccessHandler handler) throws Exception {

//        httpSecurity
//                .authorizeExchange().pathMatchers("/actuator/health/**","/nodejs-service/api-docs/**").permitAll()
//                .anyExchange().authenticated()
//                .and()
//                .oauth2ResourceServer().jwt().and()
//                .and().build();

        httpSecurity.authorizeExchange().pathMatchers("/actuator/health/**","/nodejs-service/api-docs/**","/login**").permitAll()
                .and()
                .authorizeExchange().anyExchange().authenticated()
                .and()
                .oauth2Login()
                .and()
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

}
