package com.Royal.Main.persistence.dto;

import com.Royal.Main.persistence.entity.UserFinancial;
import lombok.Data;

@Data
public class PurchaseOrderDTO {

    private UserFinancial UserFinancials;

    private String merchName;

    private String userName;
}
