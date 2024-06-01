package com.Royal.Main.persistence.dto;

import com.Royal.Main.persistence.entity.enums.MerchandiseCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class MerchandiseCreateDTO {

        @NotBlank
        @Enumerated(EnumType.STRING)
        private MerchandiseCategory category;

        @NotBlank
        @Min(value = 700) @Max(value = 5000)
        private Double merchPrice;

        @NotBlank
        private String merchName;

        @NotBlank
        @Size(min = 50, max = 1500)
        private String merchDescription;

        @NotBlank
        @Min(value = 0) @Max(value = 100)
        private Integer currentStockQuantity;

        @NotBlank
        private String merchantName;

        @NotBlank @Email @Size(min=3)
        private String email;
}