package com.Royal.Main.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long orderNumber;

    private LocalDateTime datePurchased;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    @ManyToOne
    private RoyalUserFinancials UserFinancials;

    @ManyToOne
    private RoyalMerchandise MerchandisePurchase;

    @OneToOne
    private RoyalUser royalUser;

    @ManyToOne
    private PurchaseHistory purchaseHistory;

}
