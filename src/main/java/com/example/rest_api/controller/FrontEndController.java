package com.example.rest_api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller     // klasa która mapuje żądania http na metody i zwraca nazwę widoku html
public class FrontEndController {
    @GetMapping("/")
    public String home(Authentication auth){
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        System.out.println("Logging credentials: " + userDetails);
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "loginPage";     // widok z resources/templates i bez rozszerzenia html
    }
}
