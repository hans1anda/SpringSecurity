package com.landa.SpringSecurityEx.service;

import com.landa.SpringSecurityEx.model.Users;
import com.landa.SpringSecurityEx.repository.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public UserService(IUserRepository userRepository, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder(12);
    }


    public Users register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String verify(Users user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken();
        }
        return "Fail";
    }
}
