package com.my.pet.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	SysUserDetailsServiceImpl userService;
	
	@Autowired
    private CustomAuthenticationProvider authProvider;
	
	@Bean 
	@Override 
	public AuthenticationManager 
	authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/home_admin", "/view_faculties", "/manage_users", "/add_edit_faculty", "/generate_report",
				"/processFaculty", "/sendEmails").hasAnyRole("ADMIN")
		.antMatchers("/home_user", "/view_marks", "/manage_appointments", "/updateMark").hasAnyRole("USER")
		.and().formLogin().loginPage("/login")
		.defaultSuccessUrl("/loginProc")
		.failureForwardUrl("/loginProc")
		.permitAll()
		.and().logout();
    }
	
	
}
