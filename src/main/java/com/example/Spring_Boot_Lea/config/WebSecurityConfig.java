package com.example.Spring_Boot_Lea.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import java.util.HashSet;
import java.util.Set;

@Configuration
public class WebSecurityConfig{
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception{
        security.authorizeHttpRequests(auth ->auth
                .anyRequest().authenticated()
        ).formLogin();
        return security.build();
    }


    @Bean
    InMemoryUserDetailsManager userDetailsManager(){
        Set<UserDetails> users = new HashSet<>();
        users.add(User.builder().username("fpt").password(passwordEncoder().encode("fpt")).roles("ADMIN").build());
        return new InMemoryUserDetailsManager(users);
    }
}
