package com.example.rest_api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

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
                        .loginProcessingUrl("/login_process")
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/");
    }
    @Autowired
    DataSource dataSource;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.email,u.password,u.status FROM user u WHERE u.email=?")
                .authoritiesByUsernameQuery(
                        "SELECT u.email, r.role_name FROM user u JOIN users_to_roles ur ON ur.user_id = u.user_id" +
                                " JOIN role r ON r.role_id = ur.role_id WHERE u.email=?"
                )
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


}
