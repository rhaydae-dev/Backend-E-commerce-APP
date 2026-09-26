package com.rhaydae.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationProvider authProvider) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/api/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
            	
            		// Products
            	    .requestMatchers(
            	        "/api/product/saveProduct",
            	        "/api/product/update/**",
            	        "/api/product/delete/**",
            	        "/api/product/uploadImage/**"
            	    ).hasRole("ADMIN")

            	    .requestMatchers("/api/product/**")
            	    .hasAnyRole("USER", "ADMIN")

            	    // Users
            	    .requestMatchers(
            	        "/api/user/saveUser",
            	        "/api/user/getAllUsers",
            	        "/api/user/delete/**",
            	        "/api/user/searchUsername",
            	        "/api/user/searchEmail"
            	    ).hasRole("ADMIN")

            	    .requestMatchers("/api/user/getUser/**")
            	    .hasAnyRole("USER", "ADMIN")

            	    // Orders
            	    .requestMatchers("/api/order/allOrders")
            	    .hasRole("ADMIN")

            	    .requestMatchers(
            	        "/api/order/save",
            	        "/api/order/getOrder/**",
            	        "/api/order/update/**",
            	        "/api/order/delete/**",
            	        "/api/order/user/**"
            	    ).hasAnyRole("USER", "ADMIN")

            		.anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    // AuthenticationManager is exposed so it can be injected into AuthService
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
