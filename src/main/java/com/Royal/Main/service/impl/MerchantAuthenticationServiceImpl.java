package com.Royal.Main.service.impl;

import com.Royal.Main.persistence.dto.LoginDTO;
import com.Royal.Main.persistence.dto.MerchantDTO;
import com.Royal.Main.persistence.entity.Merchant;
import com.Royal.Main.repository.MerchantRepository;
import com.Royal.Main.security.JWTUtil;
import com.Royal.Main.security.MerchantAuthenticationProvider;
import com.Royal.Main.service.MerchantAuthenticationService;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import com.Royal.Main.service.util.RoleUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service

public class MerchantAuthenticationServiceImpl implements MerchantAuthenticationService {

    private final MerchantRepository merchantRepository;
    private final MerchantAuthenticationProvider merchantAuthenticationProvider;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RoleUtility roleUtility;
    private final Logger logger = LoggerFactory.getLogger(MerchandiseServiceImpl.class);

    @Autowired
    public MerchantAuthenticationServiceImpl(MerchantRepository merchantRepository, MerchantAuthenticationProvider merchantAuthenticationProvider, JWTUtil jwtUtil, PasswordEncoder passwordEncoder, RoleUtility roleUtility) {
        this.merchantRepository = merchantRepository;
        this.merchantAuthenticationProvider = merchantAuthenticationProvider;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.roleUtility = roleUtility;
    }


    @Override
    public void createMerchantAccount(MerchantDTO merchantDTO) throws EmailAlreadyTakenException {

        //Checks if the email is already taken
        String merchantEmail = merchantDTO.getEmail();
        if (this.isEmailTaken(merchantEmail)) {
            throw new EmailAlreadyTakenException(merchantEmail);
        }

        //Create the merchant object
        Merchant merchant = Merchant.builder()
                .name(merchantDTO.getMerchantName())
                .email(merchantDTO.getEmail())
                .password(passwordEncoder.encode(merchantDTO.getPassword()))
                .isVerified(true)
                .isAccountLocked(false)
                .registrationDate(LocalDate.now())
                .role(roleUtility.getMerchantRole())
                .build();

        //Store merchant object into the repository
        merchantRepository.save(merchant);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = merchantAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info("the merchant auth is being used");
        return jwtUtil.createJWTGenerator(authentication);
    }

    @Override
    public Boolean isEmailTaken(String email) {
        return merchantRepository.existsByEmail(email);
    }



    @Override
    public void deleteMerchantAccount(MerchantDTO merchantDTO) {
        //Merchant merchant = merchantRepository.findMerchantByName(merchantDTO.getMerchantName())
    }
}
