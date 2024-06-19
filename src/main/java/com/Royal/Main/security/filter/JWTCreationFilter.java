package com.Royal.Main.security.filter;

import com.Royal.Main.security.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class JWTCreationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("JWTCreationFilter:doFilterInternal method initiated");
        Authentication usernamePasswordAuthentication = SecurityContextHolder.getContext().getAuthentication();
        //logger.info("JWTCreationFilter:doFilterInternal method initiated " +  usernamePasswordAuthentication.toString());

        if (usernamePasswordAuthentication != null) {
            String token = jwtUtil.createJWT(usernamePasswordAuthentication);
            response.addHeader("Authorization", "Bearer " + token);
            logger.info("boolean to see if the authorization token has been added"+ response.containsHeader("Authorization"));
        }
        doFilter(request, response, filterChain);
    }
}
