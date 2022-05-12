package com.yourautospa.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class CmsSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailService;
	//THIS IS BCRYPT//
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/register/**").hasAnyRole("EMPLOYEE","MANAGER")
				.antMatchers("/vehicles/**").hasRole("MANAGER")
				.antMatchers("/users/**").hasRole("MANAGER")
				.antMatchers("/customers/**").hasRole("MANAGER")
				.antMatchers("/orders/**").hasRole("MANAGER")
				.antMatchers("/products/**").hasRole("MANAGER")
				.antMatchers("/css/**").permitAll()
				.antMatchers("/scripts/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/greeter/**").permitAll()
				.anyRequest().authenticated()
				.and()				
				.formLogin()
					.loginPage("/userLogin")
					.loginProcessingUrl("/authenticateTheUser")
					.permitAll()
				.and()
				.logout().permitAll()
				.and().exceptionHandling().accessDeniedPage("/access-denied");
	}
}


