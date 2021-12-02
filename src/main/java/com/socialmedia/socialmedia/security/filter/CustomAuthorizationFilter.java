package com.socialmedia.socialmedia.security.filter;

import com.socialmedia.socialmedia.exception.AddMessageToResponse;
import com.socialmedia.socialmedia.security.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userService;

    @Autowired
    public CustomAuthorizationFilter(JwtUtil jwtUtil, UserDetailsService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/refresh_token")) {
            filterChain.doFilter(request, response);
        }
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "");
            String userName = null;
            try {
                userName = jwtUtil.extractUsername(token);
            } catch (ExpiredJwtException e) {
                AddMessageToResponse.addMessageToResponse(response, e.getMessage());
                return;
            } catch (SignatureException e) {
                AddMessageToResponse.addMessageToResponse(response, e.getMessage());
                return;
            }

            UserDetails userDetails = userService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(token, userDetails)) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                AddMessageToResponse.addMessageToResponse(response, "Token is not Valid");
                return;
            }
        } else {
            AddMessageToResponse.addMessageToResponse(response, "Token is not Passed");
            return;
        }

    }
}
