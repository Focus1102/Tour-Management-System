package com.gdu_springboot.spring_boot_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Automatically supports prefixes {bcrypt}, {noop}, {pbkdf2}, etc.
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->configurer
                // Add this line to give ADMIN access to all endpoints
                .requestMatchers("/api/**").hasRole("ADMIN")
                
                // Student API security rules
                .requestMatchers(HttpMethod.GET,"/api/students").hasAnyRole("STUDENT", "MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/students/**").hasAnyRole("STUDENT", "MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/students").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/students").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/students/**").hasRole("ADMIN")
                
                // Product API security rules
                .requestMatchers(HttpMethod.GET,"/api/products").hasAnyRole("STUDENT", "MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/products/**").hasAnyRole("STUDENT", "MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/products").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/products").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/products/**").hasRole("ADMIN")
                
                // Customer API security rules
                .requestMatchers(HttpMethod.GET,"/api/customers").hasAnyRole("STUDENT", "MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/customers/**").hasAnyRole("STUDENT", "MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/customers").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/customers").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/customers/**").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf->csrf.disable()); 
        return http.build();
    }
}
