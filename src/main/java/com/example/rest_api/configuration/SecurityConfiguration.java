package com.example.rest_api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration      // klasa o specyficznym znaczeniu implementująca globalne zabezpieczenia w aplikcji
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                // żądania wymagające logowania
                .antMatchers("/users").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/users/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .anyRequest().permitAll()
                .and().csrf().disable()
                .formLogin()
                    .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginProcessingUrl("login_process")
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/");

    }
}
