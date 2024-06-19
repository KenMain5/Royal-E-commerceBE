package com.Royal.Main.service;

import com.Royal.Main.persistence.dto.LoginDTO;
import com.Royal.Main.persistence.dto.MerchantDTO;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import com.Royal.Main.service.exceptions.ObjectNotSavedException;

public interface MerchantAuthenticationService {
    void createMerchantAccount(MerchantDTO merchantDTO) throws EmailAlreadyTakenException, ObjectNotSavedException;
    void deleteMerchantAccount(MerchantDTO merchantDTO);
    String login(LoginDTO loginDTO);
    void isEmailTaken(String email) throws EmailAlreadyTakenException;
}
