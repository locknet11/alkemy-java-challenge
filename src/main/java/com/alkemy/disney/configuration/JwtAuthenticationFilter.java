package com.alkemy.disney.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alkemy.disney.services.UserService;
import com.alkemy.disney.utils.JwtUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwt;
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return (new AntPathMatcher().match("/auth/**", request.getServletPath())
        		|| new AntPathMatcher().match("/", request.getServletPath())
        		|| new AntPathMatcher().match("/websocket", request.getServletPath())
        		|| new AntPathMatcher().match("/v2/**", request.getServletPath())
        		|| new AntPathMatcher().match("/swagger-ui.html", request.getServletPath())
        		|| new AntPathMatcher().match("/webjars/**", request.getServletPath()));
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String bearerToken = request.getHeader("Authorization");
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
							
				String jwtToken = bearerToken.substring(7);
				
				String email = jwt.extractUsername(jwtToken);
				
				UserDetails userDetails = userService.loadUserByUsername(email);
				
				if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
				
		}
		
		filterChain.doFilter(request, response);
	}

}
