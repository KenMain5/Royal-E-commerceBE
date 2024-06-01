package com.Royal.Main.persistence.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MerchantDTO {

    @NotBlank
    @Size(min=2, max=50)
    private String merchantName;

    @NotBlank @Email @Size(min=3)
    private String email;

    @NotBlank @Size(min = 7, max = 20, message = "The password needs to be seven characters minimum")
    private String password;
}


