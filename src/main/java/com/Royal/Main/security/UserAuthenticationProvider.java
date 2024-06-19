package com.Royal.Main.security;

import com.Royal.Main.persistence.entity.User;
import com.Royal.Main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);
    private final JWTUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String givenEmail = authentication.getPrincipal().toString();
        String givenPassword = authentication.getCredentials().toString();
        logger.info("this is the givenEmail "  + givenEmail.toString() + "and givenPassword" + givenPassword.toString());
        //logger.info(authentication.toString());

        //gets the user information from the database
        Optional<User> optionalUser = userRepository.getUserByEmail(givenEmail);

        //checks the credentials and does the authentication process
        if (optionalUser.isPresent()) {
            if (passwordEncoder.matches(givenPassword, optionalUser.get().getPassword())) {
                logger.info("User has been authenticated");
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(givenEmail, givenPassword, this.getGrantedList(optionalUser.get()));
                jwtUtil.createJWT(token);
                return token;
            }
        }

        logger.info("user provided wrong credentials");
        throw new com.Royal.Main.service.exceptions.AuthenticationException();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    //Gets the list of granted authorities of the user
    public List<GrantedAuthority> getGrantedList(User userInfo){
        List<GrantedAuthority> list = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole().getGivenRole());
        list.add(authority);
        return list;
    }
}
