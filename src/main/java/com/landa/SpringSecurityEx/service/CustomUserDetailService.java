package com.landa.SpringSecurityEx.service;

import com.landa.SpringSecurityEx.model.UserPrincipal;
import com.landa.SpringSecurityEx.model.Users;
import com.landa.SpringSecurityEx.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final IUserRepository userRepository;

    public CustomUserDetailService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user1 = userRepository.findByUsername(username);
        if(Objects.isNull(user1)) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User not found!");
        }

        return new UserPrincipal(user1);

    }
}
