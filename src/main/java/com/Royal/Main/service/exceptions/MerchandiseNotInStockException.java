package com.Royal.Main.service.exceptions;

//Exception thrown when an item is not in stock
public class MerchandiseNotInStockException extends Exception{
    private String itemName;

    public MerchandiseNotInStockException(String itemName) {
        super("The item " + itemName + " is currently not in stock");
        this.itemName = itemName;
    }
}
