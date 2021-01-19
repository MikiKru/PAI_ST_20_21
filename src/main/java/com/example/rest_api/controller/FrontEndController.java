package com.example.rest_api.controller;

import com.example.rest_api.model.User;
import com.example.rest_api.service.PostService;
import com.example.rest_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

@Controller     // klasa która mapuje żądania http na metody i zwraca nazwę widoku html
public class FrontEndController {
    private UserService userService;
    private PostService postService;
    @Autowired
    public FrontEndController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication auth){
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        System.out.println("Logging credentials: " + userDetails);
        model.addAttribute("email", userDetails.getUsername());
        model.addAttribute("roles", userDetails.getAuthorities());
        model.addAttribute("posts", postService.getAllPosts());
        return "home";
    }
    @GetMapping("/login")
    public String login(Model model, Authentication auth){
        // model - komunikacja między warstwani BE - FE
        // model.addAttribute(nazwa obiektu w FE, obiekt BE);
        model.addAttribute("user", new User());
        // auth == is null to niezalogowano : zalogowano
        model.addAttribute("isAuth", auth);
        return "loginPage";     // widok z resources/templates i bez rozszerzenia html
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute User user){
        user.setRegistrationDateTime(LocalDateTime.now());
        user.setStatus(true);
        userService.insertUser(user);
        return "redirect:/login";       // przekierowanie na adres metodą GET
    }
    @GetMapping("/posts/{postId}")
    public String getPost(@PathVariable("postId") int postId){
        return null;
    }
}
