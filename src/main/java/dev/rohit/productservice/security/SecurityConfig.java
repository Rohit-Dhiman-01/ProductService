package dev.rohit.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {

                http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/actuator/health").permitAll()
//                                .anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
//                 Form login handles the redirect to the login page from the
//                 authorization server filter chain
                .formLogin(Customizer.withDefaults())
                .oauth2ResourceServer((oauth)->oauth.
                        jwt(Customizer.withDefaults()));

        http.cors().disable();
        http.csrf().disable();

        return http.build();
    }
}
