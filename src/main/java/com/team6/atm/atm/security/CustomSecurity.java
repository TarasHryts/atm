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
                .antMatchers("/account/transfer**").hasRole("USER")
                .antMatchers("/account/**").hasRole("ADMIN")
                .antMatchers("/atm/add**").hasRole("ADMIN")
                .antMatchers("/atm/deposit**").hasRole("USER")
                .antMatchers("/atm/withdraw**").hasRole("USER")
                .antMatchers("/user/**").hasRole("ADMIN")
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
