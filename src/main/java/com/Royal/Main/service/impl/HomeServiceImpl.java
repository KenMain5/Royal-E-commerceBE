package com.Royal.Main.service.impl;

import com.Royal.Main.persistence.model.BannerContent;
import com.Royal.Main.persistence.model.HeroContent;
import com.Royal.Main.service.HomeService;
import com.Royal.Main.persistence.entity.Merchandise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Override
    public BannerContent getBanner() {
        return new BannerContent();
    }

    @Override
    public List<Merchandise> getAllMerch() {
        return null;
    }

    @Override
    public HeroContent getHeroContent() {
        return new HeroContent();
    }
}
