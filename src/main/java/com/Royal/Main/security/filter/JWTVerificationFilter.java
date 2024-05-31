package com.Royal.Main.security.filter;


import com.Royal.Main.security.JWTUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWTVerificationFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final Logger logger = LoggerFactory.getLogger(JWTVerificationFilter.class);

    @Autowired
    public JWTVerificationFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, JwtException {
        logger.info("JWTVerificationFilter: doFilterInternal method has been called");

        //from the request, check if there's a header that has Authorization: Bearer {token}
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jsonWebToken = authorizationHeader.substring(7);
            jwtUtil.parseJWT(jsonWebToken);
            logger.info("the auth header is not null and it starts with authorization");
        }
        doFilter(request, response, filterChain);
    }
}
