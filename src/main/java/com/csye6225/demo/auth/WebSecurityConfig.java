package com.csye6225.demo.auth;

import com.csye6225.demo.pojo.Account;
import com.csye6225.demo.pojo.AccountRepository;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import java.util.List;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private BasicAuthEntryPoint basicAuthEntryPoint;

  @Autowired
  private AccountRepository accountRepository;


  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/forgot-password").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .authenticationEntryPoint(basicAuthEntryPoint);

  }

  @Bean
  public HttpSessionStrategy httpSessionStrategy() {
    return new HeaderHttpSessionStrategy();
  }

  @Bean
  public UserDetailsService userDetailsService() {

    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("user").password(bCryptPasswordEncoder.encode("password")).roles("USER").build());
    List<Account> users = (List<Account>) accountRepository.findAll();
    for(Account a : users){
      manager.createUser(User.withUsername(a.getEmail()).password(a.getPassword()).roles("USER").build());
    }
    return manager;
  }

}
