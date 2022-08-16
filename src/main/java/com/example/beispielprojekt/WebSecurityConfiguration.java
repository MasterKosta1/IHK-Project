package com.example.beispielprojekt;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableGlobalMethodSecurity ( prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private static final String[] AUTH_WHITELIST = { "/actuator/**", "/addUser", "/addUser/**"};
	
	@Override
	protected void configure( final HttpSecurity http ) throws Exception
	{
	http.cors().and().csrf().disable().authorizeRequests()
	.antMatchers( AUTH_WHITELIST ).permitAll()
	.anyRequest().authenticated();



	}

}
