package com.landa.SpringSecurityEx.service;

import com.landa.SpringSecurityEx.model.Users;
import com.landa.SpringSecurityEx.repository.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(12);
    }


    public Users register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
