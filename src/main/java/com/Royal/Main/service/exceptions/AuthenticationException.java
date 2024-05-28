package com.Royal.Main.service.exceptions;


public class AuthenticationException extends org.springframework.security.core.AuthenticationException {
    public AuthenticationException() {
        super("The authentication has failed");
    }
}
