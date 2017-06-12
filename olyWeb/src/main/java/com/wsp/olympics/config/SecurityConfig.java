package com.wsp.olympics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .csrfTokenRepository(csrfTokenRepository())
                .and()
            .authorizeRequests()
                .antMatchers("/admin", "/admin/**").authenticated()
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login.jsp")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/j_spring_security_check?logout")
                .permitAll();
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("X-CSRF-TOKEN");
        return repository;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("orders").password("orderspw").roles("ADMIN");
    }
}
