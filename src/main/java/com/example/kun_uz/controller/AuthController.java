package com.example.kun_uz.controller;

import com.example.kun_uz.dto.AuthDTO;
import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.dto.RegistrationDTO;
import com.example.kun_uz.dto.SmsConfirmDTO;
import com.example.kun_uz.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDTO dto){
        return ResponseEntity.ok(authService.registration(dto));
    }

    @GetMapping("/registration/confirm/{id}")
    public ResponseEntity<String> registration(@PathVariable Integer id){ // TODO UUID
        return ResponseEntity.ok(authService.registrationConfirm(id));
    }

    @PostMapping("/registration/sms/confirm")
    public ResponseEntity<String> smsConfirm(@RequestBody SmsConfirmDTO dto){
        return ResponseEntity.ok(authService.smsConfirm(dto, LocalDateTime.now()));
    }

    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody  @Valid AuthDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }

}
