package com.example.rest_api.service;

import com.example.rest_api.model.User;
import com.example.rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service     // klasa implementująca logikę biznesową
public class UserService {
    // wstrzykiwanie zależności - DI
                  // kontener springa zarządza repozytorium i
    @Autowired    // umożliwia nam dostęp wtedy gdy jest potrzebny
    UserRepository userRepository;

    // INSERT INTO users VALUES (?,?,?, ...);
    public User insertUser(User user){
        return userRepository.save(user);
    }
    // SELECT * FROM users ORDER BY registration_time DESC;
    public List<User> selectUsers(){
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "registrationDateTime"));
    }
    // SELECT * FROM users WHERE user_id = ?;
    public Optional<User> getUserById(int userId){
        return userRepository.findById(userId);
    }
    // UPDATE users SET status = 1;
    public boolean activateUser(int userId){
        boolean isActivated = false;
        Optional<User> userOptional = getUserById(userId);
        if(userOptional.isPresent()){
            User userToActivate = userOptional.get();
            userToActivate.setStatus(true);
            userRepository.save(userToActivate);     // działa jak update gdy dotyczy istniejącego w repo obiektu
            isActivated = true;
        }
        return isActivated;
    }
}
