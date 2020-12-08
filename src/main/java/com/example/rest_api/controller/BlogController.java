package com.example.rest_api.controller;

import com.example.rest_api.model.Post;
import com.example.rest_api.model.User;
import com.example.rest_api.model.dtos.PostDto;
import com.example.rest_api.service.PostService;
import com.example.rest_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController     // adnotacja wykorzystywana do mapowania żądań http
public class BlogController {
    private UserService userService;
    private PostService postService;
    @Autowired
    public BlogController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        User user = userService.getUserById(3).get();
        return user.getPassword();
    }
    @GetMapping("/user={userName}&number={number}")
    public String helloMe(
            @PathVariable("userName") String userName,
            @PathVariable("number") Integer number
    ){
        return String.format("name = %s number = %d", userName, number);
    }
    @PostMapping("/users/registration")
    public User registerUser(
        @RequestParam("name") String name,
        @RequestParam("lastName") String lastName,
        @RequestParam("email") String email,
        @RequestParam("password") String password
    ){
        return userService.insertUser(new User(name,lastName,email,password, LocalDateTime.now(), "",false));
    }
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.selectUsers();
    }
    @PutMapping("/users/activate/id={userId}")
    public boolean activateUser(
            @PathVariable("userId") int userId
    ){
        return userService.activateUser(userId);
    }
    @DeleteMapping("/users/delete")
    public boolean deleteUserById(
            @RequestParam("userId") int userId
    ){
        return userService.deleteUserById(userId);
    }
    @PostMapping("/posts/publication")
    public String addNewPost(@Valid @ModelAttribute("postDto") PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Arrays.stream(bindingResult.getSuppressedFields()).forEach(System.out::println);
            return "Validation Errors";
        }
        postService.addPost(postDto);
        return "OK";
    }
}
