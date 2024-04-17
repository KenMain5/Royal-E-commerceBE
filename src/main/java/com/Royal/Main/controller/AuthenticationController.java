package com.Royal.Main.controller;

import com.Royal.Main.dto.LoginDTO;
import com.Royal.Main.dto.RegistrationDTO;
import com.Royal.Main.response.AuthenticationResponse;
import com.Royal.Main.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationServices;

    @Autowired
    public AuthenticationController(AuthenticationServiceImpl authenticationServices) {
        this.authenticationServices = authenticationServices;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationDTO registrationDTO){
        //call in the method
        //if success
            //return ok status body user is now registered
        //else return bad request
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        AuthenticationResponse authenticationResponse = authenticationServices.login(loginDTO);
        if (authenticationResponse.isSuccessful()) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", "Bearer " + authenticationResponse.getToken());
            return ResponseEntity.ok().headers(responseHeaders).body("The user is now logged in");
        }
        return ResponseEntity.badRequest().build();
    }


}

