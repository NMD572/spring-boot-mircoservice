package vn.nmd.microservice.api_gateway.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

	// Allow to view swagger-ui and api-docs without authentication
	private final String[] FREE_RESOURCE_URL = { "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",
			"/swagger-resources/**", "/aggregate/**", "/api-docs/**", };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.authorizeHttpRequests(authorize -> authorize.requestMatchers(FREE_RESOURCE_URL).permitAll()
						.anyRequest().authenticated())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.oauth2ResourceServer(oauthe2 -> oauthe2.jwt(Customizer.withDefaults())).build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.applyPermitDefaultValues();
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
