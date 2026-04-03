package com.capg.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * JwtUtil (Utility Class for JWT Operations)
 * 
 * This class is responsible for:
 * - Generating JWT tokens
 * - Validating JWT tokens
 * 
 * It uses a secret key to sign and verify tokens.
 * 
 * JWT contains:
 * - Subject (username)
 * - Issued time
 * - Expiration time
 */
public class JwtUtil {

    /**
     * Secret key used for signing the JWT
     */
    private static final String SECRET = "mysecretkeymysecretkeymysecretkey";

    /**
     * Key object generated using secret
     */
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * Generate JWT Token
     * 
     * @param username username of the user
     * @return generated JWT token
     */
    public static String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username) // set username
                .setIssuedAt(new Date()) // token creation time
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // ⏱️ 1 hour expiry
                .signWith(key) // sign with secret key
                .compact();
    }

    /**
     * Validate JWT Token
     * 
     * @param token JWT token
     * @return username (subject) if token is valid
     * @throws ExpiredJwtException if token is expired
     * @throws JwtException if token is invalid
     */
    public static String validateToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key) // verify using same key
                .build()
                .parseClaimsJws(token) // parse token
                .getBody()
                .getSubject(); // extract username
    }
}