package com.studiozero.projeto.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilterConfig securityFilterConfig;
    private final CustomAuthenticationEntryPointConfig customAuthenticationEntryPointConfig;

    private static final AntPathRequestMatcher[] URLS_PERMITIDAS = {
            new AntPathRequestMatcher("/docs"),
            new AntPathRequestMatcher("/docs/*"),
            new AntPathRequestMatcher("/docs/**"),
            new AntPathRequestMatcher("/h2-console/**"),
            new AntPathRequestMatcher("/h2-console/**/**"),
            new AntPathRequestMatcher("/employees/login"),
            new AntPathRequestMatcher("/drive"),
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-resources"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/configuration/ui"),
            new AntPathRequestMatcher("/configuration/security"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/actuator/*"),
            new AntPathRequestMatcher("/error/**"),
            new AntPathRequestMatcher("/employees/**"),
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception ->
                        exception. authenticationEntryPoint(customAuthenticationEntryPointConfig))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(URLS_PERMITIDAS).permitAll()
                        .anyRequest().authenticated()
                )
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .addFilterBefore(securityFilterConfig, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:8080")); // frontend
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
