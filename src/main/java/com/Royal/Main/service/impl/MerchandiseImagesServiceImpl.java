package com.Royal.Main.service.impl;

import com.Royal.Main.repository.MerchandiseRepository;
import com.Royal.Main.service.MerchandiseImagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MerchandiseImagesServiceImpl implements MerchandiseImagesService {
    private final String fileSystemURL = "/Users/main/Desktop/SpringRoyalStore/ImagesFileSystem/";
    private final String productionFileSystemURL = "/imagesFileSystem/";
    private final MerchandiseRepository merchandiseRepository;
    private final Logger logger = LoggerFactory.getLogger(MerchandiseServiceImpl.class);

    @Autowired
    public MerchandiseImagesServiceImpl(MerchandiseRepository merchandiseRepository) {
        this.merchandiseRepository = merchandiseRepository;
    }

    //TODO: start filling the data for clothes..
    @Override
    public List<Resource> retrieveImages(int[] idArray) {
        List<Resource> resourceList = new ArrayList<>();
        for (int id: idArray) {
            resourceList.add(this.getSingleImage(id));
        }
        return resourceList;
    }

    @Override
    public Resource getSingleImage(int imageId){
        Optional<String> OptionalStringURL = merchandiseRepository.findImageLocationById(imageId);
        if (OptionalStringURL.isPresent()) {
            try {
                Resource newResource = new UrlResource(fileSystemURL);
                return newResource;
            } catch (MalformedURLException e){
                logger.info("an error occurred", e);
            }
        }
        return null;
    }
}
