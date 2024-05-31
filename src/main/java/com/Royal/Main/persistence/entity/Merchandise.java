package com.Royal.Main.persistence.entity;

import com.Royal.Main.persistence.entity.enums.MerchandiseCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"merchant"})
public class Merchandise {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MerchandiseCategory category;

    private Double merchPrice;

    private String merchName;

    private String merchDescription;

    private Integer currentStockQuantity;


    private String imageLocation;

    @ManyToOne
    private Merchant merchant;

    private Date dateAdded;
}
