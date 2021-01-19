package com.example.rest_api.controller;

import com.example.rest_api.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String login(Model model){
        // model - komunikacja między warstwani BE - FE
        // model.addAttribute(nazwa obiektu w FE, obiekt BE);
        model.addAttribute("user", new User());
        return "loginPage";     // widok z resources/templates i bez rozszerzenia html
    }

}
