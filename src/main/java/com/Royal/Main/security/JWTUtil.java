package com.Royal.Main.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JWTUtil {


    public String createJWT(Authentication authentication){
        Date currentDate = new Date();
        long currentDateInSeconds = currentDate.getTime();
        long twoHoursToMSeconds = 2 * 60 * 60 * 1000;
        long expirationDateInMSeconds = currentDateInSeconds + twoHoursToMSeconds;

        String jwt = Jwts.builder()
                .header()
                    .keyId("HS512")
                    .and()

                .issuer("Royal")
                .claim("Principal", authentication.getPrincipal())
                .claim("Authorities", authentication.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(expirationDateInMSeconds))


                .signWith(SecurityConstants.secretKey)
                .compact();
        return jwt;
    }

    public void parseJWT(String givenJWT) throws JwtException{
        log.info("JWTUtil: parseJWT method has been called {}", givenJWT.toString());

                Claims claims = Jwts.parser()
                        .verifyWith(SecurityConstants.secretKey)
                        .build()
                        .parseSignedClaims(givenJWT) //internally match hash values, if not, throw error
                        .getPayload();

                //When we stored the authority in the JWT, it became just a list of Strings
                //so that it would be easier to transfer, currently it's not in the form
                //of Granted Authorities, so we need to change it back to that format
                String username = claims.get("Principal").toString();

                String roles = claims.get("Authorities").toString();
                List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(roles));

                UserDetails user = new User(username, "", authorities);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities()));
    }
}
