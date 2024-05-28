package com.Royal.Main.service.exceptions;

public class IncorrectCredentialsException extends Exception{
    public IncorrectCredentialsException() {
        super("The user credentials provided were not found in the system");
    }
}
