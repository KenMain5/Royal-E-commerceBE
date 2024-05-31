package com.Royal.Main.service;

import com.Royal.Main.persistence.dto.MerchandiseCreateDTO;
import com.Royal.Main.persistence.dto.MerchandiseReadDTO;
import com.Royal.Main.persistence.entity.Merchandise;
import com.Royal.Main.service.exceptions.MerchandiseNotFoundException;
import com.Royal.Main.service.exceptions.MerchandiseNotInStockException;
import com.Royal.Main.service.exceptions.MerchantNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MerchandiseService {
    void addMerchandises(List<MerchandiseCreateDTO> merchandiseCreateDTOs, List<MultipartFile> imageFiles) throws IOException, MerchantNotFoundException;

    void removeMerchandises(List<MerchandiseCreateDTO> listOfMerchandise);

    List<Merchandise> getMerchandisesCreatedByMerchant(String email) throws MerchantNotFoundException;

    MerchandiseReadDTO getSingleMerchandiseCreatedByMerchant(String email) throws MerchantNotFoundException;

}

