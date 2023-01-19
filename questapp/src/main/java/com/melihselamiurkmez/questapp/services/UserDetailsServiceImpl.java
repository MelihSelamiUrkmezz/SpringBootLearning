package com.melihselamiurkmez.questapp.services;

import com.melihselamiurkmez.questapp.entities.User;
import com.melihselamiurkmez.questapp.repository.UserRepository;
import com.melihselamiurkmez.questapp.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) throws  UsernameNotFoundException {

        User user=userRepository.findById(id).get();
        return JwtUserDetails.create(user);

    }
}
