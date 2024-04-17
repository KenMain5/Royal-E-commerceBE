package com.Royal.Main.entity;

public enum PurchaseStatus {
    PENDING("PENDING"),
    PROCESSING("PROCESSING"),
    TRANSIT("IN TRANSIT"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED");

    public String status;

    PurchaseStatus(String status){
        this.status = status;
    }
}
