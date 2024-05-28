package com.Royal.Main.persistence.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CancellationOrderDTO {
    private UUID orderNumber;
    private String merchName;
}
