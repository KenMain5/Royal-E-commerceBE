package com.Royal.Main.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToOne
    private RoyalUser royalUser;

    @OneToMany(mappedBy = "purchaseHistory")
    private List<PurchaseOrder> purchaseOrders;
}
