package com.Royal.Main.controller;

import com.Royal.Main.entity.RoyalMerchandise;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping(value = "addMerch", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMerch(List<RoyalMerchandise> merchList){

    }


}

