package com.springboot.dev_spring_boot_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(configurer ->
                configurer
                    .requestMatchers(
                        "/",
                        "/shop",
                        "/default/**",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**",
                        "/webjars/**"
                    ).permitAll()
                    .requestMatchers("/cart/**", "/api/cart/**").authenticated()
                    .requestMatchers("/admin/**").hasRole("MANAGER")
                    .requestMatchers("/system").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .formLogin(form ->
                form
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticateTheUser")
                    .defaultSuccessUrl("/shop", true)
                    .permitAll()
            )
            .logout(logout -> logout.permitAll())
            .exceptionHandling(configurer ->
                configurer.accessDeniedPage("/error")
            );

        return http.build();
    }
}
