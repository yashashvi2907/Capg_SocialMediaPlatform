package com.capg.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * JwtFilter (Security Filter)
 * 
 * This filter is used to intercept incoming HTTP requests and validate
 * the JWT (JSON Web Token) before allowing access to secured APIs.
 * 
 * Responsibilities:
 * - Extract Authorization header from request
 * - Validate JWT token
 * - Handle invalid or expired tokens
 * - Allow request to proceed if token is valid
 * 
 * Applied on:
 * - "/friends/*" endpoints (configured in Config class)
 */
public class JwtFilter implements Filter {

    /**
     * This method is executed for every incoming request
     * 
     * @param request  incoming HTTP request
     * @param response outgoing HTTP response
     * @param chain    filter chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Cast request to HttpServletRequest
        HttpServletRequest req = (HttpServletRequest) request;

        /**
         * Extract Authorization header
         * Expected format: "Bearer <token>"
         */
        String header = req.getHeader("Authorization");

        // ❌ If token is missing or invalid format
        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Token missing!");
        }

        // Extract actual token (remove "Bearer ")
        String token = header.substring(7);

        try {
            // Validate JWT token
            JwtUtil.validateToken(token);

        } catch (ExpiredJwtException e) {
            // ❌ Token expired
            throw new RuntimeException("Time's up! Login again.");

        } catch (Exception e) {
            // ❌ Invalid token
            throw new RuntimeException("Invalid token!");
        }

        // ✅ Continue request if token is valid
        chain.doFilter(request, response);
    }
}