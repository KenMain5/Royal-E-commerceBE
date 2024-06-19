package com.Royal.Main.controller;

import com.Royal.Main.persistence.dto.MerchandiseCreateDTO;
import com.Royal.Main.service.exceptions.MerchandiseNotFoundException;
import com.Royal.Main.service.exceptions.MerchantNotFoundException;
import com.Royal.Main.service.exceptions.ObjectNotSavedException;
import com.Royal.Main.service.impl.MerchandiseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/merchant/merchandise")
public class MerchandiseController {

    private final MerchandiseServiceImpl merchandiseServiceImpl;

    @Autowired
    public MerchandiseController(MerchandiseServiceImpl merchandiseServiceImpl) {
        this.merchandiseServiceImpl = merchandiseServiceImpl;
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity addMerchandise(@RequestPart("merchandiseCreateDTO") List<MerchandiseCreateDTO> merchandiseCreateDTOs,
                                         @RequestPart("merchImage") List<MultipartFile> merchImages) throws MerchantNotFoundException, IOException, ObjectNotSavedException {
            merchandiseServiceImpl.addMerchandises(merchandiseCreateDTOs, merchImages);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAllMerchandise(List<MerchandiseCreateDTO> merchandiseCreateDTOS) throws MerchandiseNotFoundException {
        merchandiseServiceImpl.removeMerchandises(merchandiseCreateDTOS);
            return ResponseEntity.noContent().build();
    }
}
