package com.Royal.Main.persistence.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;

@Data
public class LoginDTO {

    @NotBlank
    @Email
    @Size(min = 3, max = 30)
    private String email;

    @NotBlank
    @Size(min = 7, max = 20, message = "The password needs to be seven characters minimum")
    private String password;
}
