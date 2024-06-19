package com.Royal.Main.controller;

import com.Royal.Main.persistence.dto.LoginDTO;
import com.Royal.Main.persistence.dto.MerchantDTO;
import com.Royal.Main.service.MerchantAuthenticationService;
import com.Royal.Main.service.exceptions.EmailAlreadyTakenException;
import com.Royal.Main.service.exceptions.ObjectNotSavedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/merchant")
@Slf4j
public class MerchantAuthenticationController {

    private final MerchantAuthenticationService merchantAuthenticationService;

    @Autowired
    public MerchantAuthenticationController(MerchantAuthenticationService merchantAuthenticationService) {
        this.merchantAuthenticationService = merchantAuthenticationService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addMerchant(@RequestBody MerchantDTO merchantDTO) throws EmailAlreadyTakenException, ObjectNotSavedException {
        log.info("User attempting to register an account" + merchantDTO.toString());
        merchantAuthenticationService.createMerchantAccount(merchantDTO);
        return new ResponseEntity<>("Merchant Account is now created", HttpStatus.CREATED);
    }

    @PostMapping(value = "/signIn", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loginMerchant(@RequestBody LoginDTO loginDTO){
        //Call the login method and return a JWT
        String jwt = merchantAuthenticationService.login(loginDTO);

        //Create an HttpHeader to send the JWT back to the client
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(jwt);

        //Return a 201 status code and return the JWT
        return new ResponseEntity<>("Merchant is now signed in", httpHeaders,HttpStatus.CREATED);
    }
}
