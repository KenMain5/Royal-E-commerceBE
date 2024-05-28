package com.Royal.Main.persistence.model;

import com.Royal.Main.persistence.entity.Merchandise;
import lombok.Data;

import java.util.List;

@Data
public class MainPageContent {
    private BannerContent bannerContent;
    private List<Merchandise> merchandises;
    private HeroContent heroContent;
}
