package com.example.rest_api.service;

import com.example.rest_api.model.Post;
import com.example.rest_api.model.User;
import com.example.rest_api.model.dtos.PostDto;
import com.example.rest_api.model.enums.Category;
import com.example.rest_api.repository.PostRepository;
import com.example.rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Map getAggregatedPostsByCategory(){
        return postRepository.getAggregatedPostsByCategory().stream()
                .collect(Collectors.toMap(o -> o[0], o -> o[1]));
    }
    public List<Post> getAllPosts(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "publicationTime"));
    }
}
