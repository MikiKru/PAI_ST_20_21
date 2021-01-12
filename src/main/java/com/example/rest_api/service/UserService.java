package com.example.rest_api.service;

import com.example.rest_api.model.Role;
import com.example.rest_api.model.User;
import com.example.rest_api.repository.RoleRepository;
import com.example.rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service     // klasa implementująca logikę biznesową
public class UserService {
    // wstrzykiwanie zależności - DI
                  // kontener springa zarządza repozytorium i
    @Autowired    // umożliwia nam dostęp wtedy gdy jest potrzebny
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    // INSERT INTO users VALUES (?,?,?, ...);
    public User insertUser(User user){
//        Role defaultRole = roleRepository.findById(1).get();
        Optional<Role> defaultRoleOpt = roleRepository.findFirstByRoleName("ROLE_USER");
        if(defaultRoleOpt.isPresent()) {
            Role defaultRole = defaultRoleOpt.get();
            // do użytkownika przypisuję zbiór zawierający jedną rolę - default
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setRoles(new HashSet<>(Arrays.asList(defaultRole)));
            return userRepository.save(user);
        }
        return null;
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
    // DELETE FROM users WHERE user_id = ?;
    public boolean deleteUserById(int userId){
        AtomicBoolean isDeleted = new AtomicBoolean(false);
        getUserById(userId).ifPresent(user -> {
            userRepository.deleteById(userId);
            isDeleted.set(true);
        });
        return isDeleted.get();
    }
}
