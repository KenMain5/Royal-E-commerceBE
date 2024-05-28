package com.Royal.Main.service;

import com.Royal.Main.persistence.dto.LoginDTO;
import com.Royal.Main.persistence.dto.MerchantDTO;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import org.springframework.security.core.AuthenticationException;

public interface MerchantAuthenticationService {
    void createMerchantAccount(MerchantDTO merchantDTO) throws EmailAlreadyTakenException;
    void deleteMerchantAccount(MerchantDTO merchantDTO);
    String login(LoginDTO loginDTO);
    Boolean isEmailTaken(String email);
}
