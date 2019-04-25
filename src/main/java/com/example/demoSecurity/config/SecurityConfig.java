package com.example.demoSecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user123").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin123").roles("ADMIN")
                .and()
                .withUser("teller").password("{noop}teller123").roles("TELLER")
                .and()
                .withUser("super").password("{noop}super123").roles("SUPER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/cashier").hasAnyRole("USER", "TELLER")
                .antMatchers("/check").hasRole("SUPER")
                .antMatchers("/general").hasAnyRole("TELLER", "SUPER")
                .antMatchers("/shared").permitAll()
                .and()
                .httpBasic()
                .and()
                .logout();
    }
}
