package com.Royal.Main.service.impl;

import com.Royal.Main.persistence.dto.MerchandiseCreateDTO;
import com.Royal.Main.persistence.dto.MerchandiseReadDTO;
import com.Royal.Main.persistence.entity.Merchandise;
import com.Royal.Main.persistence.entity.Merchant;
import com.Royal.Main.repository.MerchandiseRepository;
import com.Royal.Main.repository.MerchantRepository;
import com.Royal.Main.service.MerchandiseService;
import com.Royal.Main.service.exceptions.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j

//@EnableRetry
public class MerchandiseServiceImpl implements MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;
    private final MerchantServiceImpl merchantService;
    private final String fileSystemURL = "/Users/main/Desktop/SpringRoyalStore/ImagesFileSystem/";
    private final String productionFileSystemURL = "/imagesFileSystem/";

    @Autowired
    public MerchandiseServiceImpl(MerchandiseRepository merchandiseRepository, MerchantServiceImpl merchantService) {
        this.merchandiseRepository = merchandiseRepository;
        this.merchantService = merchantService;
    }

    @Override
    @Transactional
    public void addMerchandises(List<MerchandiseCreateDTO> merchandiseCreateDTOs,
                                          List<MultipartFile> imageFiles) throws IOException, MerchantNotFoundException, ObjectNotSavedException {
        for (int i = 0; i < merchandiseCreateDTOs.size(); i++) {
            //Simplifying the variable names
            MerchandiseCreateDTO currentDTO = merchandiseCreateDTOs.get(i);
            MultipartFile currentImageFile = imageFiles.get(i);

            //gets the file path
            String filePath = fileSystemURL + currentImageFile.getName();

            //Ensuring merchant is already in the database
            Merchant merchant = merchantService.getMerchantByEmail(currentDTO.getEmail());

            //transfers data from the DTO to the merchandise object to be stored
            Merchandise merchandise = this.createMerchandiseFromDTO(currentDTO, merchant, filePath);
            this.saveMerchandise(merchandise);

            currentImageFile.transferTo(new File(filePath));
        }
    }

    @Override
//    @Transactional
    public void removeMerchandises(List<MerchandiseCreateDTO> merchandiseCreateDTOS) throws MerchandiseNotFoundException {
        for (MerchandiseCreateDTO merchandiseCreateDTO : merchandiseCreateDTOS){
            String merchandiseName = merchandiseCreateDTO.getMerchName();

            Merchandise merchandise = this.findMerchandiseByMerchName(merchandiseName);
            merchandiseRepository.deleteByMerchName(merchandiseName);
            String filePath = merchandise.getImageLocation();
            File file = new File(filePath);
            file.delete();
        }
    }

    @Override
    public List<Merchandise> getMerchandisesCreatedByMerchant(String email) throws MerchantNotFoundException {
        Merchant merchant = merchantService.getMerchantByEmail(email);
        Optional<List<Merchandise>> optionalMerchandiseList = merchandiseRepository.findMerchandisesByMerchant(merchant);
        if (optionalMerchandiseList.isPresent()) {

            //for each item.. get the image location
            //get the location.. store the image in a multi part file..
            //then create the merchandiseReadDTO
            //
            return optionalMerchandiseList.get();
        }

        return null;
        //TODO: Not Urgent: get image data from the URL file
    }


    //What is this??
    @Override
    public MerchandiseReadDTO getSingleMerchandiseCreatedByMerchant(String email) throws MerchantNotFoundException {
        Merchant merchant = merchantService.getMerchantByEmail(email);
        List<Merchandise> merchandiseList = this.getMerchandiseListByMerchant(merchant);
        Merchandise merchandise = merchandiseList.get(0);

        return MerchandiseReadDTO.builder()
                .category(merchandise.getCategory())
                .merchPrice(merchandise.getMerchPrice())
                .merchName(merchandise.getMerchName())
                .merchDescription(merchandise.getMerchDescription())
                .currentStockQuantity(merchandise.getCurrentStockQuantity())
                .dateAdded(merchandise.getDateAdded())
                .merchantName(merchandise.getMerchant().getName())
                .merchImageURL(merchandise.getImageLocation())
                .build();
    }

    public List<Merchandise> getMerchandiseListByMerchant(Merchant merchant) throws MerchantNotFoundException {
        Optional<List<Merchandise>> optionalMerchandiseList = merchandiseRepository.findMerchandisesByMerchant(merchant);
        if (optionalMerchandiseList.isPresent()) {
            return optionalMerchandiseList.get();
        } else {
            throw new MerchantNotFoundException();
        }
    }

    public Merchandise findMerchandiseByMerchName(String merchandiseName) throws MerchandiseNotFoundException {
        Optional<Merchandise> optionalMerchandise = merchandiseRepository.findByMerchName(merchandiseName);
        if (optionalMerchandise.isPresent()) {
            return optionalMerchandise.get();
        } else {
            throw new MerchandiseNotFoundException(merchandiseName);
        }
    }

    public void saveMerchandise(Merchandise merchandise) throws ObjectNotSavedException {
        Merchandise savedMerchandise = merchandiseRepository.save(merchandise);
        if (savedMerchandise == null) {
            throw new ObjectNotSavedException();
        }
    }

    protected Merchandise createMerchandiseFromDTO(MerchandiseCreateDTO merchandiseCreateDTO, Merchant merchant, String filePath){
        return Merchandise.builder()
                .merchName(merchandiseCreateDTO.getMerchName())
                .merchPrice(merchandiseCreateDTO.getMerchPrice())
                .merchDescription(merchandiseCreateDTO.getMerchDescription())
                .currentStockQuantity(merchandiseCreateDTO.getCurrentStockQuantity())
                .category(merchandiseCreateDTO.getCategory())
                .merchant(merchant)
                .dateAdded(new Date())
                .imageLocation(filePath)
                .build();
    }
}
