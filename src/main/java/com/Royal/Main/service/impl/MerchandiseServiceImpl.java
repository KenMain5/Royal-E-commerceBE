package com.Royal.Main.service.impl;

import com.Royal.Main.persistence.dto.MerchandiseCreateDTO;
import com.Royal.Main.persistence.entity.Merchandise;
import com.Royal.Main.persistence.entity.Merchant;
import com.Royal.Main.repository.MerchandiseRepository;
import com.Royal.Main.repository.MerchantRepository;
import com.Royal.Main.service.MerchandiseService;
import com.Royal.Main.service.exceptions.MerchandiseNotFoundException;
import com.Royal.Main.service.exceptions.MerchandiseNotInStockException;
import com.Royal.Main.service.exceptions.MerchantNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@EnableRetry
public class MerchandiseServiceImpl implements MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;
    private final MerchantRepository merchantRepository;
    private final Logger logger = LoggerFactory.getLogger(MerchandiseServiceImpl.class);
    private final String fileSystemURL = "/Users/main/Desktop/SpringRoyalStore/ImagesFileSystem/";
    private final String productionFileSystemURL = "/imagesFileSystem/";

    @Autowired
    public MerchandiseServiceImpl(MerchandiseRepository merchandiseRepository, MerchantRepository merchantRepository) {
        this.merchandiseRepository = merchandiseRepository;
        this.merchantRepository = merchantRepository;
    }

    @Override
    //@Transactional
    public void addMerchandises(List<MerchandiseCreateDTO> merchandiseCreateDTOs,
                                          List<MultipartFile> imageFiles) throws IOException, MerchantNotFoundException{
        Merchandise merchandise = new Merchandise();
        List<Merchandise> merchandisesSaved = new ArrayList<>();
        String filePath;

        for (int i = 0; i < merchandiseCreateDTOs.size(); i++) {
            //Simplifying the variable names
            MerchandiseCreateDTO currentDTO = merchandiseCreateDTOs.get(i);
            MultipartFile currentImageFile = imageFiles.get(i);
            Merchant verifiedMerchant;

            //gets the file path
            filePath = fileSystemURL + currentImageFile.getName();

            //Ensuring merchant is already in the database
            Optional<Merchant> optionalMerchant =  merchantRepository.findMerchantByEmail(currentDTO.getEmail());
            if (optionalMerchant.isPresent()) {
                verifiedMerchant = optionalMerchant.get();
            } else {
                throw new MerchantNotFoundException(currentDTO.getMerchantName());
            }

            //TODO: this can be put in refactored into a util class
            //transfers data from the DTO to the merchandise object to be stored
            merchandise.setMerchName(currentDTO.getMerchName());
            merchandise.setMerchPrice(currentDTO.getMerchPrice());
            merchandise.setMerchDescription(currentDTO.getMerchDescription());
            merchandise.setCurrentStockQuantity(currentDTO.getCurrentStockQuantity());
            merchandise.setCategory(currentDTO.getCategory());

            merchandise.setMerchant(verifiedMerchant);
            merchandise.setDateAdded(new Date());
            merchandise.setImageLocation(filePath);
            Merchandise savedMerchandise= merchandiseRepository.save(merchandise);

            if (savedMerchandise != null) {
                currentImageFile.transferTo(new File(filePath));
            }
        }
    }

    @Override
//    @Transactional
    public void removeMerchandises(List<MerchandiseCreateDTO> merchandiseCreateDTOS) {
        Merchandise merchandise = new Merchandise();

        for (MerchandiseCreateDTO merchandiseCreateDTO : merchandiseCreateDTOS){
            String merchandiseName = merchandiseCreateDTO.getMerchName();
            Optional<Merchandise> optionalMerchandise = merchandiseRepository.findByMerchName(merchandiseName);
            if (optionalMerchandise.isPresent()) {
                merchandiseRepository.deleteByMerchName(merchandiseName);
                String filePath = optionalMerchandise.get().getImageLocation();
                File file = new File(filePath);
                file.delete();
            }
        }
    }

    @Override
    public List<Merchandise> getMerchandises() {
        List<Merchandise> merchandises = merchandiseRepository.findAll();
        return merchandises;
        //TODO: Not Urgent: get image data from the URL file
    }
}
