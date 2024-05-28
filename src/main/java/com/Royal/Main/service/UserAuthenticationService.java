package com.Royal.Main.service;

import com.Royal.Main.persistence.dto.LoginDTO;
import com.Royal.Main.persistence.dto.RegistrationDTO;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import org.springframework.security.core.AuthenticationException;

public interface UserAuthenticationService {
    Boolean isEmailTaken(String email);

    void createUserAccount(RegistrationDTO registrationDTO) throws EmailAlreadyTakenException;
    String login(LoginDTO loginDTO) throws AuthenticationException;

}
