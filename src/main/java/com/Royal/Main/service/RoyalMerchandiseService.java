package com.Royal.Main.service;

import com.Royal.Main.entity.RoyalMerchandise;

import java.util.List;

public interface RoyalMerchandiseService {
    void addMerchandise(List<RoyalMerchandise> listOfMerchandise);
    void removeMerchandise(List<RoyalMerchandise> listOfMerchandise);
    void deleteMerchandise(List<RoyalMerchandise> listOfMerchandise);

}
