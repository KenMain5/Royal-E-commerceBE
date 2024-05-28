package com.Royal.Main.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "user")
public class UserFinancial {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private int cardData;

    private String userAddress;

    private String nameOnCard;

    @ManyToOne
    private User user;

    @OneToOne
    private PurchaseOrder purchaseOrder;
}
