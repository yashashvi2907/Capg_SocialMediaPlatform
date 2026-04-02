package com.capg.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String header = req.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			throw new RuntimeException("Token missing!");
		}

		String token = header.substring(7);

		try {
			JwtUtil.validateToken(token);
		} catch (ExpiredJwtException e) {
			throw new RuntimeException("Time's up! Login again.");
		} catch (Exception e) {
			throw new RuntimeException("Invalid token!");
		}

		chain.doFilter(request, response);
	}
}
