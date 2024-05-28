package com.Royal.Main.service.exceptions;

//Exception thrown when a merchandise is sent to the backend but its not in the system
public class MerchandiseNotFoundException extends Exception{
    private String merchantName;

    public MerchandiseNotFoundException(String merchantName){
        super("The merchandise " + merchantName + " was not found");
        this.merchantName = merchantName;
    }
}
