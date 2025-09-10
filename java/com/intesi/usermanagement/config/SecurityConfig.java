package com.intesi.usermanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private static final String[] URLS_TO_ALLOW_WITHOUT_AUTH = { "/v3/api-docs*/**", "/configuration/**",
			"/swagger-ui.html", "/swagger-ui/**", "/api/**", "/h2-console/**", "/actuator/**" };

	@Value("${spring.application.allowedOrigins}")
	private String[] allowedOrigins;


	

	  
  @Bean 
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(
        		URLS_TO_ALLOW_WITHOUT_AUTH
        ).permitAll()
        .anyRequest().authenticated()
      )
//      .httpBasic(basic -> {})          // opzionale: Basic Auth sugli endpoint protetti
      .formLogin(form -> form.disable())
      .headers(h -> h.frameOptions(f -> f.sameOrigin())); // per H2 console

    return http.build();
    
  
  }
  


}