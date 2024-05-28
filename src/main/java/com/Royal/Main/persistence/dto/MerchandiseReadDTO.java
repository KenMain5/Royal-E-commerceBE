package com.Royal.Main.persistence.dto;

import com.Royal.Main.persistence.entity.enums.MerchandiseCategory;
import com.Royal.Main.persistence.entity.Merchant;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class MerchandiseReadDTO {
    @Enumerated(EnumType.STRING)
    private MerchandiseCategory category;

    @NotNull //below 5000
    private Double merchPrice;

    @NotNull //min, max count of string
    private String merchName;

    @NotNull //min, max count
    private String merchDescription;

    @NotNull //?
    private Integer currentStockQuantity;

    @NotNull
    private Date dateAdded;

    @NotNull
    private Merchant merchant;

    @NotNull
    private MultipartFile merchImage;
}
