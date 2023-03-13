package com.bennett.livecodingv2.service;

import com.bennett.livecodingv2.DTO.LoginDto;
import com.bennett.livecodingv2.DTO.RegResponse;
import com.bennett.livecodingv2.model.AuthRequest;
import com.bennett.livecodingv2.model.User;
import com.bennett.livecodingv2.response.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface Services {
    RegResponse register(User user);
    AuthResponse login(LoginDto loginDto);
    User getUserInformation(Long id);
    ResponseEntity updateCustomerInformation(Long id, User UpdatedUser);
    String generateCode(Long id);
    ResponseEntity<String> authenticate(AuthRequest request);
}
