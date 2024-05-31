package com.Royal.Main.persistence.entity;

import com.Royal.Main.persistence.entity.enums.PurchaseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"user"})
public class PurchaseOrder {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private UUID orderNumber;

    private LocalDateTime orderPlaced;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    @OneToOne
    private UserFinancial userFinancials;

    private String merchName;

    private Double merchPrice;

    @ManyToOne
    private User user;
}
