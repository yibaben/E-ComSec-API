package com.bennett.livecodingv2.controller;

import com.bennett.livecodingv2.model.AuthRequest;
import com.bennett.livecodingv2.service.Services;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@AllArgsConstructor
public class AuthController {
    private final Services services;
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest request){
        return services.authenticate(request);
    }

}
