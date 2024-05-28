package com.Royal.Main.controller;

import com.Royal.Main.service.impl.MerchandiseImagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MerchandiseImagesController {

    private final MerchandiseImagesServiceImpl merchandiseImagesService;

    @Autowired
    public MerchandiseImagesController(MerchandiseImagesServiceImpl merchandiseImagesService) {
        this.merchandiseImagesService = merchandiseImagesService;
    }


    @GetMapping("/sssss")
    public ResponseEntity<List<Resource>> getImages(@RequestBody int[] idArray){
        List<Resource> resourceList = merchandiseImagesService.retrieveImages(idArray);
        return new ResponseEntity<>(resourceList, HttpStatus.OK);
    }
}
