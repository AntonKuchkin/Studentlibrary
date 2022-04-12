package com.example.studentlibrary.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/librarian/**").authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
    }
    @Bean
    public UserDetailsService librarians(){
        UserDetails librarian = User.builder()
                .username("librarian")
                .password("{bcrypt}$2a$12$oPZrVo24s.jcZp3F7uH1bOA5gOHKgPgV7o9c73HcVbz.sIGdSx7Oq") //пароль 100
                .roles("LIBRARIAN")
                .build();
        return new InMemoryUserDetailsManager(librarian);
    }
}
