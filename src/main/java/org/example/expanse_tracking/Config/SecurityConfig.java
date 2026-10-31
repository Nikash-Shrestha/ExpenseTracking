
package org.example.expanse_tracking.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Response.ApiResponse;
import org.example.expanse_tracking.Util.JwtAuthFilter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .exceptionHandling(exception -> exception

                        // Missing or invalid authentication
                        .authenticationEntryPoint((request, response, ex) -> {
                            response.setStatus(
                                    HttpServletResponse.SC_UNAUTHORIZED
                            );
                            response.setContentType(
                                    MediaType.APPLICATION_JSON_VALUE
                            );

                            objectMapper.writeValue(
                                    response.getOutputStream(),
                                    ApiResponse.error(
                                            401,
                                            "Authentication required. Please provide a valid JWT token."
                                    )
                            );
                        })

                        // Authenticated but insufficient permissions
                        .accessDeniedHandler((request, response, ex) -> {
                            response.setStatus(
                                    HttpServletResponse.SC_FORBIDDEN
                            );
                            response.setContentType(
                                    MediaType.APPLICATION_JSON_VALUE
                            );

                            objectMapper.writeValue(
                                    response.getOutputStream(),
                                    ApiResponse.error(
                                            403,
                                            "You do not have permission to access this resource."
                                    )
                            );
                        })
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/users/register",
                                "/api/users/login"
                        ).permitAll()

                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
