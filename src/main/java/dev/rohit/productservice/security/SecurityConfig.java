package dev.rohit.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers(HttpMethod.GET ,"/products/getProducts").hasAnyRole("user")
//                        .requestMatchers(HttpMethod.GET,"/products/getProducts/{id}").hasAuthority("user")
//                        .requestMatchers(HttpMethod.GET,"/products/thirdPartyService").hasAuthority("user")
//                        .requestMatchers(HttpMethod.GET,"/products/thirdPartyService/{id}").hasAuthority("user")
//                        .anyRequest().hasAnyAuthority("admin")
//                          .anyRequest().hasAnyRole("admin")
//                          .anyRequest().hasRole("admin")
//                )
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
//                .formLogin(Customizer.withDefaults());

//        http.cors().disable();
//        http.csrf().disable();

//        return http.build();
//    }
}
