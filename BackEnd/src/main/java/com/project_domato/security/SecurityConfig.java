package com.project_domato.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project_domato.security.jwt.AuthEntryPointJwt;
import com.project_domato.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	@Autowired
	private AuthEntryPointJwt authEntryPointJwt;

	@Autowired
	private AuthTokenFilter authTokenFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

		// CSRF DISABLED....
		httpSecurity.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
				.authorizeHttpRequests(request -> request
						.requestMatchers(HttpMethod.POST, "/users/register", "/auth/login").permitAll()
						.requestMatchers(HttpMethod.GET,"/users/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")
						
						.requestMatchers(HttpMethod.GET, "/food/**", "/user/exists").permitAll()
						.requestMatchers(HttpMethod.POST,"/food/**").hasRole("ADMIN")
						
						.requestMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE,"/admin/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
						
						.requestMatchers(HttpMethod.POST,"/cart/**").hasRole("USER")
						.requestMatchers(HttpMethod.DELETE,"/cart/**").hasRole("USER")
						.requestMatchers(HttpMethod.GET,"/cart/**").hasRole("ADMIN")
						
						
						.requestMatchers(HttpMethod.POST,"/orders/**").hasRole("USER")
						.requestMatchers(HttpMethod.GET,"/orders/**").hasRole("ADMIN")
						
						.requestMatchers(HttpMethod.POST,"/address/**").hasRole("USER")
						.requestMatchers(HttpMethod.GET,"/address/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE,"/address/**").hasRole("ADMIN")
						
						.requestMatchers(HttpMethod.POST,"/payments/**").hasRole("USER")
						.anyRequest().authenticated());

		httpSecurity.exceptionHandling(authentication -> authentication.authenticationEntryPoint(authEntryPointJwt));

		httpSecurity.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

//	PASSWORD ENCODER.....
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:4200"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);

//	        For more restrictive CORS settings, you can specify allowed headers and methods explicitly
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;

	}
}
