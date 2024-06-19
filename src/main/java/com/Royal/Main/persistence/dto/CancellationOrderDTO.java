package com.Royal.Main.persistence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class CancellationOrderDTO {

    @NotBlank @Positive
    private UUID orderNumber;

    @NotBlank
    private String merchName;
}
