package com.team6.atm.atm.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("{noop}1")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}1")
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/error**").hasRole("ADMIN")
                .antMatchers("/accounts/transfer**").hasRole("USER")
                .antMatchers("/accounts/**").hasRole("ADMIN")
                .antMatchers("/atms/add**").hasRole("ADMIN")
                .antMatchers("/atms/deposit**").hasRole("USER")
                .antMatchers("/atms/withdraw**").hasRole("USER")
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/swagger**").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable();
    }
}
