package com.example.rest_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller     // klasa która mapuje żądania http na metody i zwraca nazwę widoku html
public class FrontEndController {
    @GetMapping("/login")
    public String login(){
        return "loginPage";     // widok z resources i bez rozszerzenia html
    }
}
