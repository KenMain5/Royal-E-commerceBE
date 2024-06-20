package com.Royal.Main.service.impl;

import com.Royal.Main.persistence.entity.Merchant;
import com.Royal.Main.repository.MerchantRepository;
import com.Royal.Main.security.JWTUtil;
import com.Royal.Main.security.MerchantAuthenticationProvider;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import com.Royal.Main.service.exceptions.ObjectNotSavedException;
import com.Royal.Main.service.util.RoleUtility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MerchantAuthenticationServiceImplTest {

    private MerchantRepository mockedMerchantRepository;
    private MerchantAuthenticationProvider mockedMerchantAuthenticationProvider;
    private JWTUtil mockedJWTUtil;
    private PasswordEncoder mockedPasswordEncoder;
    private RoleUtility mockedRoleUtility;
    private MerchantAuthenticationServiceImpl mockedMerchantAuthenticationServiceImpl;


    @BeforeEach
    void setUp() {
        mockedMerchantRepository = mock(MerchantRepository.class);
        mockedMerchantAuthenticationProvider = mock(MerchantAuthenticationProvider.class);
        mockedJWTUtil = mock(JWTUtil.class);
        mockedPasswordEncoder = mock(PasswordEncoder.class);
        mockedRoleUtility = mock(RoleUtility.class);

        mockedMerchantAuthenticationServiceImpl = new MerchantAuthenticationServiceImpl(
                mockedMerchantRepository,
                mockedMerchantAuthenticationProvider,
                mockedJWTUtil,
                mockedPasswordEncoder,
                mockedRoleUtility
        );
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(
                mockedMerchantRepository,
                mockedMerchantAuthenticationProvider,
                mockedJWTUtil,
                mockedPasswordEncoder,
                mockedRoleUtility);
    }

    @Test
    void createMerchantAccount() {

    }

    @Test
    void login() {

    }

    @Test
    void createMerchantFromMerchantDTO() {

    }

    @Test
    void saveMerchantMethodShouldNotThrowExceptionWhenSavedSuccessfully() {
        Merchant mockedMerchant = mock(Merchant.class);
        Merchant savedMerchant = mock(Merchant.class);

        when(mockedMerchantRepository.save(mockedMerchant)).thenReturn(savedMerchant);

        assertDoesNotThrow(() -> mockedMerchantAuthenticationServiceImpl.saveMerchant(mockedMerchant));
    }

    @Test
    void saveMerchantShouldThrowObjectNotSavedExceptionWhenSaveFails() {
        Merchant mockedMerchant = mock(Merchant.class);

        when(mockedMerchantRepository.save(mockedMerchant)).thenReturn(null);

        assertThrows(ObjectNotSavedException.class, () -> {
            mockedMerchantAuthenticationServiceImpl.saveMerchant(mockedMerchant);
        });
    }

    @Test
    void AssertIsEmailTakenMethodThrowsException() {
        String testEmail = "test123@gmail.com";

        when(mockedMerchantRepository.existsByEmail(testEmail)).thenReturn(true);

        assertThrows(EmailAlreadyTakenException.class,
                () -> mockedMerchantAuthenticationServiceImpl.isEmailTaken(testEmail));
    }

    @Test
    void deleteMerchantAccount() {

    }
}


/*
The reason we don't test on development databases is because we want the test to run fast since every
push we make it will

* /
 */