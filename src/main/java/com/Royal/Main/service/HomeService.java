package com.Royal.Main.service;

import com.Royal.Main.persistence.model.BannerContent;
import com.Royal.Main.persistence.model.HeroContent;
import com.Royal.Main.persistence.entity.Merchandise;

import java.util.List;

public interface HomeService {
    BannerContent getBanner();
    List<Merchandise> getAllMerch();
    HeroContent getHeroContent();
}
