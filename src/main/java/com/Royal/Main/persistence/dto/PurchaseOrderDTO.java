package com.Royal.Main.persistence.dto;

import com.Royal.Main.persistence.entity.UserFinancial;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PurchaseOrderDTO {

    @NotBlank
    private UserFinancial UserFinancials;

    @NotBlank
    private String merchName;

    @NotBlank
    private String userName;
}
