package com.Royal.Main.service.impl;

import com.Royal.Main.configuration.SecurityConfig;
import com.Royal.Main.dto.LoginDTO;
import com.Royal.Main.dto.RegistrationDTO;
import com.Royal.Main.entity.RoyalUser;
import com.Royal.Main.response.AuthenticationResponse;
import com.Royal.Main.repository.RoyalUserRepository;
import com.Royal.Main.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RoyalUserRepository royalUserRepository;
    private final SecurityConfig securityConfig;

    @Autowired
    public AuthenticationServiceImpl(RoyalUserRepository royalUserRepository, SecurityConfig securityConfig) {
        this.royalUserRepository = royalUserRepository;
        this.securityConfig = securityConfig;
    }

    @Override
    public AuthenticationResponse logout() {
        return null;
    }

    @Override
    public AuthenticationResponse login(LoginDTO loginDTO) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if (this.isEmailTaken(loginDTO.getEmail())) {
            Optional<RoyalUser> correctUser = royalUserRepository.getRoyalUserByEmail(loginDTO.getEmail());//all fetch are optional
            //Royal User -> Changed into Optional, makes changes below


            PasswordEncoder passwordEncoder = securityConfig.createPasswordEncoder();
            Boolean isVerified = passwordEncoder.matches(correctUser.getPassword(), loginDTO.getPassword());

            if (isVerified) {
                //create String var and call JWT Generator
                authenticationResponse.setToken(null); //change to token
                authenticationResponse.setSuccessful(true);
                authenticationResponse.setResponseMessage("The user is verified");
                return authenticationResponse;

                //call user password auth filter
                //https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html
                //add to security context
            }
        }
        authenticationResponse.setSuccessful(false);
        authenticationResponse.setResponseMessage("the user is not found in the database");
        return authenticationResponse;
    }

    @Override
    public AuthenticationResponse createAccount(RegistrationDTO registrationDTO) {
        //checks if email is already used
        if (this.isEmailTaken()) {
            return new AuthenticationResponse(false, "The email has already been taken", null);
        }

        //creates a new entity with given data
        RoyalUser currentUser = new RoyalUser();
        currentUser.setEmail(registrationDTO.getEmail());
        currentUser.
                setPassword("{bcrypt}" + securityConfig
                        .createPasswordEncoder()
                        .encode(registrationDTO.getPass()));
        currentUser.setDob(registrationDTO.getDob());
        currentUser.setFirstName(registrationDTO.getFirstName());
        currentUser.setLastName(registrationDTO.getLastName());
        currentUser.setIsVerified(false);
        royalUserRepository.save(currentUser);

        //checks if the email is now registered
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (royalUserRepository.existsByEmail(registrationDTO.getEmail())) {
            authenticationResponse.setSuccessful(true);
            authenticationResponse.setResponseMessage("The user has been successfully saved");
            return authenticationResponse;
        }

        //If email is not in the system, the method failed
        authenticationResponse.setSuccessful(false);
        authenticationResponse.setResponseMessage("Server Error: Try Again");
        return authenticationResponse;
    }

    @Override
    public Boolean isEmailTaken(String email) {
        if (royalUserRepository.existsByEmail(email)) {
            return true;
        } else {
            return false;
        }
    }
}
