package com.Royal.Main.service.impl;

import com.Royal.Main.entity.RoyalMerchandise;
import com.Royal.Main.repository.RoyalMerchandiseRepository;
import com.Royal.Main.service.RoyalMerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoyalMerchandiseServiceImpl implements RoyalMerchandiseService {

    private final RoyalMerchandiseRepository royalMerchandiseRepository;

    @Autowired
    public RoyalMerchandiseServiceImpl(RoyalMerchandiseRepository royalMerchandiseRepository) {
        this.royalMerchandiseRepository = royalMerchandiseRepository;
    }

    @Override
    public void addMerchandise(List<RoyalMerchandise> listOfMerchandise) {
        royalMerchandiseRepository.saveAll(listOfMerchandise);
        //we need a response?
    }

    @Override
    public void removeMerchandise(List<RoyalMerchandise> listOfMerchandise) {
        royalMerchandiseRepository.saveAll(listOfMerchandise);
        //we need a response?


    }

    @Override
    public void deleteMerchandise(List<RoyalMerchandise> listOfMerchandise) {
        royalMerchandiseRepository.deleteAll(listOfMerchandise);
        //we need a response?

    }



}
