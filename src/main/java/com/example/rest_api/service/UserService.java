package com.example.rest_api.service;

import com.example.rest_api.model.User;
import com.example.rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service     // klasa implementująca logikę biznesową
public class UserService {
    // wstrzykiwanie zależności - DI
                  // kontener springa zarządza repozytorium i
    @Autowired    // umożliwia nam dostęp wtedy gdy jest potrzebny
    UserRepository userRepository;

    // INSERT INTO user VALUES (?,?,?, ...);
    public User insertUser(User user){
        return userRepository.save(user);
    }
}
