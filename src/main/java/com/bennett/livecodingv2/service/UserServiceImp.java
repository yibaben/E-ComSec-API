package com.bennett.livecodingv2.service;

import com.bennett.livecodingv2.DTO.LoginDto;
import com.bennett.livecodingv2.DTO.RegResponse;
import com.bennett.livecodingv2.exception.UserExistsException;
import com.bennett.livecodingv2.exception.UserNotFoundException;
import com.bennett.livecodingv2.model.AuthRequest;
import com.bennett.livecodingv2.model.User;
import com.bennett.livecodingv2.repository.UserRepository;
import com.bennett.livecodingv2.response.AuthResponse;
import com.bennett.livecodingv2.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements Services{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public RegResponse register(User user) {
        userRepository.findUserByEmail(user.getEmail()).ifPresent(user1 -> {throw new UserExistsException("Email already exist");});
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPhone_Num(user.getPhone_Num());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        String code = codeGenerator();
        while (userRepository.findUserByUserCode(code).isPresent()){
            code = codeGenerator();
        }
        newUser.setUserCode(code);
        userRepository.save(newUser);
            return new RegResponse("Successfully Registered!", code);
        }


    @Override
    public AuthResponse login(LoginDto loginDto) {
        User guestUser = findUserByEmail(loginDto.getEmail());
        AuthResponse authResponse = null;
        if(guestUser != null){
            if(guestUser.getPassword().equals(loginDto.getPassword()));
            authResponse = new AuthResponse("User Successfully Login in", LocalDateTime.now());
        }else {
            authResponse = new AuthResponse("Password Not Correct", LocalDateTime.now());
        }
        return authResponse;

    }


    @Override
    public User getUserInformation(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Invalid user id provided"));
    }

    @Override
    public ResponseEntity<User> updateCustomerInformation(Long id, User updatedUser) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid user id provided"));
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone_Num(updatedUser.getPhone_Num());
        User savedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(savedUser);
    }

    private User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("User With email: " + email + " Not Found "));
    }


    public String generateCode(Long id) {
        User dbUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid user id provided"));
        return codeGenerator();
    }

    @Override
    public ResponseEntity<String> authenticate(AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if(auth.isAuthenticated()) {
            String token = "Bearer = " + jwtService.generateToken(new org.springframework.security.core.userdetails.User(request.getUsername(), request.getPassword(), new ArrayList<>()));
            return new ResponseEntity<>(token, HttpStatus.OK);
        }else {
            return null;
        }

    }

    public String codeGenerator() {
        String ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int CODE_LENGTH = 6;
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
            sb.append(ALPHANUMERIC_CHARS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
