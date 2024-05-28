package com.Royal.Main.service.exceptions;

//Exception thrown when an email is already being used by a different user
public class MerchantNotFoundException extends Exception{
    private String merchantName;

    public MerchantNotFoundException(String merchantName) {
        super("The merchant " + merchantName + " was not found in the system");
        this.merchantName = merchantName;
    }
}
