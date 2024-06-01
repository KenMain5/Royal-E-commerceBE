package com.Royal.Main.persistence.dto;

import com.Royal.Main.persistence.entity.enums.MerchandiseCategory;
import com.Royal.Main.persistence.entity.Merchant;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Data
@Builder
public class MerchandiseReadDTO {
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
    private Date dateAdded;

    @NotBlank
    private String merchantName;

    @NotBlank
    private String merchImageURL;
}
