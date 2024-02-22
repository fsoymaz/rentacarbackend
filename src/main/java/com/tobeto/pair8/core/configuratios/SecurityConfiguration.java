package com.tobeto.pair8.core.configuratios;

import com.tobeto.pair8.core.filter.JwtAuthFilter;
import com.tobeto.pair8.entities.concretes.Role;
import com.tobeto.pair8.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter;

    private final UserDetailsService userService;

    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/customers/**",
            "/api/corporateCustomers/**",
            "/api/rentals/**",
            "/api/models/**",
            "/api/colors/**",
            "/api/auths/**",
            "api/brands/**",
            "/api/cars/**",
            "/api/users/**",
            "api/imagedata/**",
            "api/test/**",
            "/api/invoices/**",
            "/api/locations/**",
            "/api/creditsCard/**",




    };



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URLS).permitAll()
                        .requestMatchers("swagger-ui/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/getByEmail").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/brands/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/colors/**").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
       return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{

        return configuration.getAuthenticationManager();
    }



}
