package com.Royal.Main.service.exceptions;

//Exception thrown when an email is already being used by a different user
public class EmailAlreadyTakenException extends Exception{
    private String email;

    public EmailAlreadyTakenException( String email) {
        super("The email " + email + "is already taken");
        this.email = email;
    }
}
