package com.example.demo.config;

import java.io.IOException;
import java.util.ArrayList;

import com.example.demo.config.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
@SuppressWarnings("hiding")
@Component
public class JwtRequestFilter<JwtUserDetailsService> extends OncePerRequestFilter{

	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	throws ServletException, IOException {
	final String requestTokenHeader = request.getHeader("Authorization");
	System.out.println(requestTokenHeader);
	
	String username = null;
	String jwtToken = null;
	
	if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	jwtToken = requestTokenHeader.substring(7);
	try {
	username = jwtTokenUtil.getUsernameFromToken(jwtToken);
	} catch (IllegalArgumentException e) {
	System.out.println("Unable to get JWT Token");
	} catch (ExpiredJwtException e) {
	System.out.println("JWT Token has expired");
	}
	} else {
	logger.warn("JWT Token does not begin with Bearer String");
	}
	if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		
	UserDetails userDetails = null;
	
	if("azhagu".equals(username)) {
		 userDetails=new User("azhagu","$2a$10$YXbiQ1QAcRDZZi9UHfhPZOA0uB7DntJ3kCbhNHw0SoK9W8DrvM3bS",
				new ArrayList<>());
	}
	else
	{
		throw new UsernameNotFoundException("User Name not Found");
	}
	
	if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
	userDetails, null, userDetails.getAuthorities());
	usernamePasswordAuthenticationToken
	.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	
	SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	}
	}
	chain.doFilter(request, response);
	}

}
