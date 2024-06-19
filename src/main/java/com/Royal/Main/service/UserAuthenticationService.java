package com.Royal.Main.service;

import com.Royal.Main.persistence.dto.LoginDTO;
import com.Royal.Main.persistence.dto.RegistrationDTO;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import com.Royal.Main.service.exceptions.ObjectNotSavedException;
import org.springframework.security.core.AuthenticationException;

public interface UserAuthenticationService {
    void isEmailTaken(String email) throws EmailAlreadyTakenException;

    void createUserAccount(RegistrationDTO registrationDTO) throws EmailAlreadyTakenException, ObjectNotSavedException;
    String login(LoginDTO loginDTO) throws AuthenticationException;

}
