package com.Royal.Main.service.exceptions;

//Exception thrown when an email is already being used by a different user
public class MerchantNotFoundException extends Exception{

    public MerchantNotFoundException() {
        super("The merchant was not found in the system");
    }
}
