package com.Royal.Main.service.impl;


import com.Royal.Main.persistence.entity.User;
import com.Royal.Main.security.UserAuthenticationProvider;
import com.Royal.Main.service.UserAuthenticationService;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import com.Royal.Main.security.config.SecurityConfig;
import com.Royal.Main.persistence.dto.LoginDTO;
import com.Royal.Main.persistence.dto.RegistrationDTO;
import com.Royal.Main.repository.UserRepository;
import com.Royal.Main.security.JWTUtil;
import com.Royal.Main.service.util.RoleUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);
    private final JWTUtil jwtUtil;
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final RoleUtility roleUtility;


    @Autowired
    public UserAuthenticationServiceImpl(UserRepository userRepository, SecurityConfig securityConfig, PasswordEncoder passwordEncoder, JWTUtil jwtUtil, UserAuthenticationProvider userAuthenticationProvider, RoleUtility roleUtility) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.roleUtility = roleUtility;
    }

    public String login(LoginDTO loginDTO) throws AuthenticationException {
            logger.info("UserAuthenticationServiceImpl:login, this is the loginDTO {}", loginDTO);
            Authentication authentication = userAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtUtil.createJWTGenerator(authentication);
        }

    public void createUserAccount(RegistrationDTO registrationDTO) throws EmailAlreadyTakenException {
        //checks if email is already used
        if (this.isEmailTaken(registrationDTO.getEmail())) {
            throw new EmailAlreadyTakenException(registrationDTO.getEmail());
        }

        //creates a new entity with given data
        User currentUser = User.builder()
                .email(registrationDTO.getEmail())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .dob(registrationDTO.getDob())
                .isVerified(false)
                .isAccountLocked(false)
                .registrationDate(LocalDate.now())
                .userFinancials(null)
                .roles(Collections.singletonList(roleUtility.getUserRole()))
                .build();

        //saves the user into the repository
        userRepository.save(currentUser);
    }

    public Boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }
}
