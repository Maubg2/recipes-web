package com.edison.app.recipesweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String homeController(){
        return "Hello world";
    }

    //@PostMapping

}
