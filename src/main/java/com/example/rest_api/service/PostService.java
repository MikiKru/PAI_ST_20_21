package com.example.rest_api.service;

import com.example.rest_api.model.Post;
import com.example.rest_api.model.User;
import com.example.rest_api.model.dtos.PostDto;
import com.example.rest_api.repository.PostRepository;
import com.example.rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Post addPost(PostDto postDto){
        Optional<User> userOptional = userRepository.findById(postDto.getAuthorId());
        return userOptional.map(user ->
                postRepository.save(
                        new Post(postDto.getTitle(), postDto.getContent(), postDto.getCategory(), user))
        ).orElse(null);
    }
}
