package com.example.SS.demo.Service;

import com.example.SS.demo.Entity.User;
import com.example.SS.demo.Repository.UserRepository;
import com.example.SS.demo.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService service;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User createUser(User user) {
        User newUser = new User();
        newUser.setCreatedAt(LocalDate.now());
        newUser.setUsername(user.getUsername());
        //now we store user password in encrypted form by using bcrypt library
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setEmailAddress(user.getEmailAddress());
        newUser.setRoles(user.getRoles());
        User save = userRepository.save(newUser);
        return save;
    }

    //as we try to change and get hold in authentication manager soo we will modify it
    //we are asking authentication manager like is user is authenticated or not
    // we are doing this manually, as we are modify basic auth
    public String verify(User user) {
        // we pass our credential in UsernamePasswordAuthenticationToken
        //its like sending a box without stamp, and receiving it with stamp
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {

            return service.generateToken(user.getUsername());
        }
        return "No Authenticated";
    }

    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User u = user.get();
        return u;
    }

    public List<User> getUsers() {
        List<User> all = userRepository.findAll();
        if (!all.isEmpty()) {
            return all;
        }
        throw new RuntimeException("No users found");
    }
}
