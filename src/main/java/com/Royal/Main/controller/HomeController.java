package com.Royal.Main.controller;


import com.Royal.Main.persistence.model.MainPageContent;
import com.Royal.Main.service.impl.HomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final HomeServiceImpl homeService;

    @Autowired
    public HomeController(HomeServiceImpl homeService) {
        this.homeService = homeService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHomePage(){
        //TODO: move all this to the implementation
        MainPageContent mainPageContent = new MainPageContent();
        mainPageContent.setBannerContent(homeService.getBanner());
        mainPageContent.setMerchandises(homeService.getAllMerch());
        mainPageContent.setHeroContent(homeService.getHeroContent());

        return ResponseEntity.ok(mainPageContent);
    }

    @GetMapping("/{category}")
    public ResponseEntity getHomePageCategory(@PathVariable String category){
        return new ResponseEntity("testing", HttpStatus.ACCEPTED);
        //need a method for each category? or not needed?
        //need method to grab items of that category..
    }
}
