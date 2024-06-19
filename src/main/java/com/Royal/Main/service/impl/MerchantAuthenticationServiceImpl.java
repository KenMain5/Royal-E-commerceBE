package com.Royal.Main.service.impl;

import com.Royal.Main.persistence.dto.LoginDTO;
import com.Royal.Main.persistence.dto.MerchantDTO;
import com.Royal.Main.persistence.entity.Merchant;
import com.Royal.Main.repository.MerchantRepository;
import com.Royal.Main.security.JWTUtil;
import com.Royal.Main.security.MerchantAuthenticationProvider;
import com.Royal.Main.service.MerchantAuthenticationService;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import com.Royal.Main.service.exceptions.ObjectNotSavedException;
import com.Royal.Main.service.util.RoleUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class MerchantAuthenticationServiceImpl implements MerchantAuthenticationService {

    private final MerchantRepository merchantRepository;
    private final MerchantAuthenticationProvider merchantAuthenticationProvider;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RoleUtility roleUtility;

    @Autowired
    public MerchantAuthenticationServiceImpl(MerchantRepository merchantRepository, MerchantAuthenticationProvider merchantAuthenticationProvider, JWTUtil jwtUtil, PasswordEncoder passwordEncoder, RoleUtility roleUtility) {
        this.merchantRepository = merchantRepository;
        this.merchantAuthenticationProvider = merchantAuthenticationProvider;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.roleUtility = roleUtility;
    }

    @Override
    public void createMerchantAccount(MerchantDTO merchantDTO) throws EmailAlreadyTakenException, ObjectNotSavedException {
        //Checks if the email is already taken
        this.isEmailTaken(merchantDTO.getEmail());

        //Create the merchant object
        Merchant merchant = this.createMerchantFromMerchantDTO(merchantDTO);

        //Store merchant object into the repository
        this.saveMerchant(merchant);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = merchantAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.createJWT(authentication);
    }

    public Merchant createMerchantFromMerchantDTO(MerchantDTO merchantDTO){
        return Merchant.builder()
                .name(merchantDTO.getMerchantName())
                .email(merchantDTO.getEmail())
                .password(passwordEncoder.encode(merchantDTO.getPassword()))
                .isVerified(true)
                .isAccountLocked(false)
                .registrationDate(LocalDate.now())
                .role(roleUtility.getMerchantRole())
                .build();
    }

    public void saveMerchant(Merchant merchant) throws ObjectNotSavedException {
        Merchant savedMerchant = merchantRepository.save(merchant);
        if (savedMerchant == null) {
            throw new ObjectNotSavedException();
        }
    }

    @Override
    public void isEmailTaken(String email) throws EmailAlreadyTakenException {
        Boolean isTaken = merchantRepository.existsByEmail(email);
        if (isTaken) {
            throw new EmailAlreadyTakenException(email);
        }
    }

    @Override
    public void deleteMerchantAccount(MerchantDTO merchantDTO) {
        //Merchant merchant = merchantRepository.findMerchantByName(merchantDTO.getMerchantName())
    }
}
