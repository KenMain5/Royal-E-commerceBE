package com.Royal.Main.service.exceptions;

public class ObjectNotSavedException extends Exception{
    public ObjectNotSavedException() {
        super("There was an exception saving this object");
    }
}
