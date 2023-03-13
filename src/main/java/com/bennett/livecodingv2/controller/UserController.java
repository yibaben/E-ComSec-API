package com.bennett.livecodingv2.controller;

import com.bennett.livecodingv2.model.User;
import com.bennett.livecodingv2.service.Services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final Services services;
    @PostMapping(value = "/create")
    public ResponseEntity register(@RequestBody User user){
        log.info("User with UniqueCode {} is Successfully Registered", user.getUserCode());
        return new ResponseEntity<>(services.register(user), HttpStatus.CREATED);
    }
    @GetMapping("/retrieve/{id}")
    public ResponseEntity<User> getUser(@PathVariable (value = "id") Long id){
        log.info("User Information Successfully Retrieved");
        return new ResponseEntity<>(services.getUserInformation(id), HttpStatus.OK);
    }
    @PutMapping("update/{id}")
    public ResponseEntity updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
        log.info("User with UniqueCode {} is Successfully Updated", user.getUserCode());
        return new ResponseEntity<>(services.updateCustomerInformation(id, user), HttpStatus.ACCEPTED);
    }
    @GetMapping("/code/{id}")
    public ResponseEntity<String> generateCode(@PathVariable(value = "id") Long id){
        log.info("User Alphanumeric 6-digit code Successfully Generated");
        return new ResponseEntity<>(services.generateCode(id), HttpStatus.CREATED);
    }

}
