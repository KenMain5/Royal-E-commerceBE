package com.Royal.Main.entity;


import jakarta.persistence.*;

@Entity
public class RoyalMerchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoyalMerchandiseCategory category;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    private Double merchPrice;

    private String merchName;

    private String merchDescription;

    private Integer currentStockQuantity;
}
