package com.example.SS.demo.Security;

import com.example.SS.demo.Entity.User;
import com.example.SS.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    //implement parent method
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(username);
        if (byUsername == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // to make this method return type UerDetails
        // we will create our own class which implements UserDetails
        return new CustomUserDetils(byUsername);

    }
}
