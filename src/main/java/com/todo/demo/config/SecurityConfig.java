package com.todo.demo.config;

import com.todo.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentails, true from member where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from member where username=?")
                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // TODO Auto-generated method stub
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/home").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/todos").and().logout().logoutSuccessUrl("/logout");}
}