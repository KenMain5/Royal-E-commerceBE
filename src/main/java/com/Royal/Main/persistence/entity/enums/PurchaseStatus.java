package com.Royal.Main.persistence.entity.enums;

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
