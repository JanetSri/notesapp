package com.smithmicro.notesapp.config;

import java.util.logging.Logger;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@Configuration
@EnableWebSecurity
@EnableTransactionManagement
public class DemoSecuityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserDetailsService myUserDetailService;
	
	
	
	private Logger logger = Logger.getLogger(getClass().getName());

	
	
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                  
    	logger.info("Entering security" );
    	
    	
    	auth.userDetailsService(myUserDetailService);
    	
    	
    	
    	
    	//Authentication authe = SecurityContextHolder.getContext().getAuthentication();
    	
    	//logger.info("name"+authe.getName() );
    	//auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username, password, enabled"
              //  + " from users where username=?");
    }
	
	
	
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// secures all REST endpoints under "/api/notes"
		http.authorizeRequests()
		.antMatchers("/api/notes/**").authenticated()
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
		
		
				
		// Why disable CSRF?
		//
		// Spring Security 5 has CSRF enabled by default. You would need to send over CSRF tokens.
		// However, CSRF generally does not apply for REST APIs. CSRF protection is a request that could be processed by a browser by normal users.
		// If you are only creating a REST service that is used by non-browser clients, you will likely want to disable CSRF protection.
		//
		// For more details, see this link:
		// http://www.tothenew.com/blog/fortifying-your-rest-api-using-spring-security/
		
		// Why disable sessions?
		//
		// For our application, we would like avoid the use of cookies for sesson tracking. This should force the REST client
		// to enter user name and password for each request. However, this is not always the case depending on the REST client / browser 
		// you are using. Your mileage will vary here (for example, this doesn't work in Eclipse embedded browser).
		//
		// For more details, see this link
		// http://www.baeldung.com/spring-security-session				
		
	}
	
	 

	
}