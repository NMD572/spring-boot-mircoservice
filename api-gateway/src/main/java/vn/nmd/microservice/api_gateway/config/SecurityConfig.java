package vn.nmd.microservice.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	// Allow to view swagger-ui and api-docs without authentication
	private final String[] FREE_RESOURCE_URL = {"/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",
			"/swagger-resources/**", "/aggregate/**", "/api-docs/**",};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(FREE_RESOURCE_URL).permitAll()
				.anyRequest().authenticated())
				.oauth2ResourceServer(oauthe2 -> oauthe2.jwt(Customizer.withDefaults())).build();
	}
}
