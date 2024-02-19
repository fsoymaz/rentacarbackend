package com.tobeto.pair8.core.filter;


import com.tobeto.pair8.core.services.JwtService;
import com.tobeto.pair8.entities.concretes.User;
import com.tobeto.pair8.repositories.UserRepository;
import com.tobeto.pair8.services.abstracts.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    private final UserService userService;

    private final UserRepository userRepository;

    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtHeader = request.getHeader("Authorization");

        if (jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
            String jwt = jwtHeader.substring(7);

            // Access token'ı doğrula ve kullanıcı adını çıkar
            String email = jwtService.extractUser(jwt);
            var user2=userRepository.findByEmail(email).orElseThrow();



            if (email != null) {
                UserDetails user = userService.loadUserByUsername(email);

                // Eğer access token geçerliyse
                if (jwtService.validateToken(jwt, user)) {
                    // Access token geçerliyse, authenticationToken oluştur ve güvenlik bağlamına ekle
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    // Eğer access token geçerli değilse, refresh token kontrolü yap
                    String refreshTokenHeader = request.getHeader("Refresh-Token");

                    if (refreshTokenHeader != null) {
                        String refreshToken = refreshTokenHeader.substring(7); // Refresh token

                        // Refresh token'ı doğrula ve kullanıcı adını çıkar
                        String refreshUsername = jwtService.extractUser(refreshToken);

                        if (refreshUsername != null) {
                            UserDetails refreshUser = userService.loadUserByUsername(refreshUsername);

                            // Eğer refresh token geçerliyse, yeni bir access token oluştur
                            if (jwtService.validateToken(refreshToken, refreshUser)) {
                                String newAccessToken = jwtService.generateToken(refreshUsername,user2);

                                // Yeni access token'ı response header'ına ekle
                                response.setHeader("Authorization", "Bearer " + newAccessToken);

                                // Authentication nesnesini güncelle
                                UsernamePasswordAuthenticationToken authenticationToken =
                                        new UsernamePasswordAuthenticationToken(refreshUser, null, refreshUser.getAuthorities());
                                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                            }


                        }
                    }
                }
            }
        }

        // İşlemi devam ettir
        filterChain.doFilter(request, response);
    }
}