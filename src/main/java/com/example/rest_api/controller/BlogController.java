package com.example.rest_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController     // adnotacja wykorzystywana do mapowania żądań http
public class BlogController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "Hello world!";
    }
    @GetMapping("/user={userName}&number={number}")
    public String helloMe(
            @PathVariable("userName") String userName,
            @PathVariable("number") Integer number
    ){
        return String.format("name = %s number = %d", userName, number);
    }
    @PostMapping("/user/registration")
    public boolean registerUser(
        @RequestParam("name") String name,
        @RequestParam("password") String password
    ){
        System.out.printf("Name = %s Password = %s", name, password);
        if(password.length() >= 8){
            return true;
        }
        return false;
    }

}
