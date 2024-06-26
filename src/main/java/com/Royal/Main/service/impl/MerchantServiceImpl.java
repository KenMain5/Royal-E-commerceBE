package com.Royal.Main.service.impl;

import com.Royal.Main.persistence.entity.Merchant;
import com.Royal.Main.repository.MerchantRepository;
import com.Royal.Main.service.exceptions.MerchantNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantServiceImpl {

    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Merchant getMerchantByEmail(String email) throws MerchantNotFoundException {
        Optional<Merchant> optionalMerchant = merchantRepository.getMerchantByEmail(email);
        if (optionalMerchant.isPresent()) {
            return optionalMerchant.get();
        } else {
            throw new MerchantNotFoundException();
        }
    }
}
