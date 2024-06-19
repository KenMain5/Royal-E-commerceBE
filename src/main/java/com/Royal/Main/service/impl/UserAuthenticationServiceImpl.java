package com.Royal.Main.service.impl;



import com.Royal.Main.persistence.entity.User;
import com.Royal.Main.security.UserAuthenticationProvider;
import com.Royal.Main.service.UserAuthenticationService;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import com.Royal.Main.persistence.dto.LoginDTO;
import com.Royal.Main.persistence.dto.RegistrationDTO;
import com.Royal.Main.repository.UserRepository;
import com.Royal.Main.security.JWTUtil;
import com.Royal.Main.service.exceptions.ObjectNotSavedException;
import com.Royal.Main.service.util.RoleUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final RoleUtility roleUtility;


    @Autowired
    public UserAuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil, UserAuthenticationProvider userAuthenticationProvider, RoleUtility roleUtility) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.roleUtility = roleUtility;
    }

    public String login(LoginDTO loginDTO) throws AuthenticationException {
            log.info("UserAuthenticationServiceImpl:login, this is the loginDTO {}", loginDTO);
            Authentication authentication = userAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtUtil.createJWT(authentication);
        }

    public void createUserAccount(RegistrationDTO registrationDTO) throws EmailAlreadyTakenException, ObjectNotSavedException {
        //checks if email is already used
        this.isEmailTaken(registrationDTO.getEmail());

        //creates a user entity with the given data
        User currentUser = this.createUserFromRegistrationDTO(registrationDTO);

        //saves the user into the repository
        this.saveUser(currentUser);
    }


    public User createUserFromRegistrationDTO(RegistrationDTO registrationDTO){
        return User.builder()
                .email(registrationDTO.getEmail())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .dob(registrationDTO.getDob())
                .isVerified(false)
                .isAccountLocked(false)
                .registrationDate(LocalDate.now())
                .userFinancials(null)
                .role(roleUtility.getUserRole())
                .build();
    }

    public void saveUser(User user) throws ObjectNotSavedException {
        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            throw new ObjectNotSavedException();
        }
    }

    public void isEmailTaken(String email) throws EmailAlreadyTakenException{
        Boolean isTaken = userRepository.existsByEmail(email);
        if (isTaken) {
            throw new EmailAlreadyTakenException(email);
        }
    }
}
