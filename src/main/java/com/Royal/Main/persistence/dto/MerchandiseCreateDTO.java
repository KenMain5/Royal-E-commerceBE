package com.Royal.Main.persistence.dto;

import com.Royal.Main.persistence.entity.enums.MerchandiseCategory;
import lombok.Data;

@Data
public class MerchandiseCreateDTO {
        //@Enumerated(EnumType.STRING)
        private MerchandiseCategory category;

        //@NotNull //below 5000
        private Double merchPrice;

        //@NotNull //min, max count of string
        private String merchName;

        //@NotNull //min, max count
        private String merchDescription;

        //@NotNull //?
        private Integer currentStockQuantity;

        //@NotNull
        private String merchantName;

        private String email;
}