package com.Royal.Main.persistence.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class MerchantDTO {

    private String merchantName;

    @NotNull
    private String email;

    @NotNull
    private String password;
}


