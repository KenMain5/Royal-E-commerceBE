package com.Royal.Main.controller;

import com.Royal.Main.service.impl.UserAuthenticationServiceImpl;
import com.Royal.Main.service.exceptions.AuthenticationException;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import com.Royal.Main.persistence.dto.LoginDTO;
import com.Royal.Main.persistence.dto.RegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserAuthenticationController {

    private final UserAuthenticationServiceImpl authenticationServiceImpl;
    private final Logger logger = LoggerFactory.getLogger(UserAuthenticationController.class);

    @Autowired
    public UserAuthenticationController(UserAuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    @PostMapping(value = "/user/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody RegistrationDTO registrationDTO) throws EmailAlreadyTakenException {
        authenticationServiceImpl.createUserAccount(registrationDTO);
        return new ResponseEntity("The account has now been created", HttpStatus.CREATED);
    }

    //@Valid
    @PostMapping(value = "/user/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@RequestBody LoginDTO loginDTO) throws AuthenticationException{
        logger.info("UserAuthentication:: loginUser - this is the login information" + loginDTO);
        String token = authenticationServiceImpl.login(loginDTO);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + token);
        return ResponseEntity.ok().headers(responseHeaders).body("The user is now logged in");
    }
}

