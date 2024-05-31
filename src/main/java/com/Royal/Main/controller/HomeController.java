package com.Royal.Main.controller;


import com.Royal.Main.persistence.model.MainPageContent;
import com.Royal.Main.security.JWTUtil;
import com.Royal.Main.service.impl.HomeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
//@RequestMapping("/home")

public class HomeController {

    private final HomeServiceImpl homeService;
    private final JWTUtil jwtUtil;




    @GetMapping(value = "/home/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHomePage(){
        //TODO: move all this to the implementation
        MainPageContent mainPageContent = new MainPageContent();
        mainPageContent.setBannerContent(homeService.getBanner());
        mainPageContent.setMerchandises(homeService.getAllMerch());
        mainPageContent.setHeroContent(homeService.getHeroContent());

        return ResponseEntity.ok(mainPageContent);
    }

    @GetMapping("/home/{category}")
    public ResponseEntity getHomePageCategory(@PathVariable String category){
        return new ResponseEntity("testing", HttpStatus.ACCEPTED);
        //need a method for each category? or not needed?
        //need method to grab items of that category..
    }

    @GetMapping("/")
    public ResponseEntity testing123(){
        //it goes here after login..
        //call the JWT from here?
        return new ResponseEntity("testing", HttpStatus.ACCEPTED);
    }

    @PostMapping("login/success")
    public ResponseEntity successfulLogin(){
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/user/cart")
    public ResponseEntity getUserCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return new ResponseEntity(name, HttpStatus.CREATED);
    }
}
