package com.bennett.livecodingv2.security;

import com.bennett.livecodingv2.exception.UserNotFoundException;
import com.bennett.livecodingv2.model.User;
import com.bennett.livecodingv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class AuthUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  throws UserNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(()-> new UserNotFoundException(
                username + " was not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
