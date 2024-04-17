package com.Royal.Main.service;


import com.Royal.Main.response.AuthenticationResponse;
import com.Royal.Main.dto.LoginDTO;
import com.Royal.Main.dto.RegistrationDTO;

public interface AuthenticationService {
    AuthenticationResponse logout();
    AuthenticationResponse login(LoginDTO);
    AuthenticationResponse createAccount(RegistrationDTO);

    Boolean isEmailTaken();
}
