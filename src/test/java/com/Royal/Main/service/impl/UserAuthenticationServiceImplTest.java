package com.Royal.Main.service.impl;

import com.Royal.Main.persistence.dto.RegistrationDTO;
import com.Royal.Main.persistence.entity.User;
import com.Royal.Main.repository.UserRepository;
import com.Royal.Main.security.JWTUtil;
import com.Royal.Main.security.UserAuthenticationProvider;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import com.Royal.Main.service.exceptions.ObjectNotSavedException;
import com.Royal.Main.service.util.RoleUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserAuthenticationServiceImplTest {
    private RegistrationDTO registrationDTO;
    private UserRepository mockedUserRepository;
    private PasswordEncoder mockedPasswordEncoder;
    private JWTUtil mockedJwtUtil;
    private UserAuthenticationProvider mockedUserAuthenticationProvider;
    private RoleUtility mockedRoleUtility;
    private UserAuthenticationServiceImpl mockedUserAuthenticationService;

    @BeforeEach
    void setUp() {
        mockedUserRepository = mock(UserRepository.class);
        mockedPasswordEncoder = mock(PasswordEncoder.class);
        mockedJwtUtil = mock(JWTUtil.class);
        mockedUserAuthenticationProvider = mock(UserAuthenticationProvider.class);
        mockedRoleUtility = mock(RoleUtility.class);

        mockedUserAuthenticationService = new UserAuthenticationServiceImpl(
                mockedUserRepository,
                mockedPasswordEncoder,
                mockedJwtUtil,
                mockedUserAuthenticationProvider,
                mockedRoleUtility
        );

        registrationDTO = new RegistrationDTO();
        registrationDTO.setEmail("test123@gmail.com");
        registrationDTO.setPassword("testing123");
        registrationDTO.setFirstName("Ken");
        registrationDTO.setLastName("Zeah");
        registrationDTO.setDob(LocalDate.now());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(
                mockedUserRepository,
                mockedPasswordEncoder,
                mockedJwtUtil,
                mockedUserAuthenticationProvider,
                mockedRoleUtility);
    }

//    @Test
//    void login() {
//
//    }
//
//    @Test
//    void createUserAccount() {
//
//    }


//    @Test
//    void createUserFromRegistrationDTOShouldNotReturnNull() {
//        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);
////        Role role = new Role();
////        role.setId(1L);
////        role.setGivenRole("ROLE_USER");
//
//        when(mockedRoleUtility.getUserRole()).thenReturn(role);
//
//        assertNotEquals(user, null);
//    }

    @Test
    void createUserFromRegistrationDTOShouldHaveCorrectEmail() {
        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);

        assertEquals(user.getEmail(), registrationDTO.getEmail());
    }

//    @Test
//    void createUserFromRegistrationDTOShouldHaveCorrectPassword() {
//        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);
//
//        assertEquals(user.getPassword(), registrationDTO.getPassword());
//    }

    @Test
    void createUserFromRegistrationDTOShouldHaveCorrectFirstName() {
        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);

        assertEquals(user.getFirstName(), registrationDTO.getFirstName());
    }

    @Test
    void createUserFromRegistrationDTOShouldHaveCorrectLastName() {
        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);

        assertEquals(user.getLastName(), registrationDTO.getLastName());
    }

    @Test
    void createUserFromRegistrationDTOShouldHaveCorrectDOB() {
        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);

        assertEquals(user.getDob(), registrationDTO.getDob());
    }

    @Test
    void createUserFromRegistrationDTOShouldNotBeVerified() {
        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);

        assertEquals(user.getIsVerified(), false);
    }

    @Test
    void createUserFromRegistrationDTOShouldNotHaveAccountLocked() {
        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);

        assertEquals(user.getIsAccountLocked(), false);
    }

//    @Test
//    void createUserFromRegistrationDTOShouldHaveCorrectRegistrationDate() {
//        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);
//
//        assertEquals(user.getRegistrationDate(),);
//    }

    @Test
    void createUserFromRegistrationDTOShouldHaveUserFinancialsNull() {
        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);

        assertEquals(user.getUserFinancials(), null);
    }
//
//    @Test
//    void createUserFromRegistrationDTOShouldHaveUserRole() {
//        User user = mockedUserAuthenticationService.createUserFromRegistrationDTO(registrationDTO);
//
//        assertEquals(user.getRole().getGivenRole(), );
//    }








      /*
        *  return User.builder()

                .registrationDate(LocalDate.now())
                .role(roleUtility.getUserRole())
                .build();*/





    @Test
    void saveUserShouldThrowObjectNotSavedExceptionWhenNotSaved() {
        User user = new User();

        when(mockedUserRepository.save(user)).thenReturn(null);

        assertThrows(ObjectNotSavedException.class, () -> mockedUserAuthenticationService.saveUser(user));
    }

    @Test
    void saveUserWouldNotThrowObjectNotSavedExceptionWhenSavedSuccessfully() {
        User user = new User();
        User savedUser = new User();

        when(mockedUserRepository.save(user)).thenReturn(savedUser);

        assertDoesNotThrow(() -> mockedUserAuthenticationService.saveUser(user));
    }

    @Test
    void isEmailTakenShouldThrowEmailAlreadyTakenExceptionIfEmailNotRegistered() {
        String testEmail = "test123@gmail.com";

        when(mockedUserRepository.existsByEmail(testEmail)).thenReturn(false);

        assertDoesNotThrow(() -> mockedUserAuthenticationService.isEmailTaken(testEmail));
    };


    @Test
    void isEmailTakenWouldNotThrowEmailAlreadyTakenExceptionIfEmailIsRegistered() {
        String testEmail = "test123@gmail.com";

        when(mockedUserRepository.existsByEmail(testEmail)).thenReturn(true);

        assertDoesNotThrow(() -> {
            mockedUserAuthenticationService.isEmailTaken(testEmail);
        });
    }


}