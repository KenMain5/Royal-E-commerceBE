package com.Royal.Main.service.exceptions;

public class EmailAlreadyTakenException extends Exception{
    private String email;

    public EmailAlreadyTakenException( String email) {
        super("The email " + email + " was already taken");
        this.email = email;
    }
}
