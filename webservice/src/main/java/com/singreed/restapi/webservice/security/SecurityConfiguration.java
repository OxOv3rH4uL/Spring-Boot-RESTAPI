package com.singreed.restapi.webservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filter( HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		
		http.httpBasic(withDefaults());
		http.csrf().disable();
		return http.build();
	}

}
