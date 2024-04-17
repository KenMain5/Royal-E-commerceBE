package com.Royal.Main.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class RoyalUserFinancials {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private int cardData;

    private String userAddress;

    private String nameOnCard;

    @ManyToOne
    private RoyalUser royalUser;

    @OneToMany
    private List<PurchaseOrder> purchaseOrder;




}
