package com.tobeto.pair8.core.services;

import com.tobeto.pair8.entities.concretes.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.key}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private long EXPIRATION;

    @Value("${jwt.refresh_token.expiration}")
    private long REFRESH_EXPIRATION;

    public String generateToken(String email, User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email,user);
    }

    public String generateRefreshToken(String email ,User user) {
        Map<String, Object> claims = new HashMap<>();
        return createRefreshToken(claims, email,user);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String email = extractUser(token);
        Date expirationDate = extractExpiration(token);
        return userDetails.getUsername().equals(email) && !expirationDate.before(new Date());
    }

    private Date extractExpiration(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

    public String extractUser(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    private String createToken(Map<String, Object> claims, String email, User user) {
        return Jwts.builder()
                .setClaims(claims)
                .claim("id",user.getId())
                .claim("role",user.getAuthorities())
                .claim("username",user.getUsername())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String createRefreshToken(Map<String, Object> claims, String email ,User user) {
        return Jwts.builder()
                .setClaims(claims)
                .claim("id",user.getId())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}