package com.example.demo.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.authentication.User;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    	 Optional<User> userOptional = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
                 
    	 userOptional.orElseThrow(() ->new UsernameNotFoundException
    			 ("User not found with username or email: "+ usernameOrEmail));
          
          
          User user = userOptional.get();
          GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
          Collection<GrantedAuthority> authorities = Collections.singletonList(authority);
          
          return new org.springframework.security.core.userdetails.User(
                  user.getEmail(),
                  user.getPassword(),
                  authorities);
    }
   
}


