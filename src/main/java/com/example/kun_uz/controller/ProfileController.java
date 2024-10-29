package com.example.kun_uz.controller;

import com.example.kun_uz.Enum.ProfileRole;
import com.example.kun_uz.ExceptionHandler.AppBadException;
import com.example.kun_uz.dto.JwtDTO;
import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.dto.RegistrationDTO;
import com.example.kun_uz.entity.ProfileEntity;
import com.example.kun_uz.service.AuthService;
import com.example.kun_uz.service.ProfileService;
import com.example.kun_uz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;


    @Autowired
    AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }

    @GetMapping("/registration/confirm/{id}")
    public ResponseEntity<String> registration(@PathVariable Integer id) {
        return ResponseEntity.ok(authService.registrationConfirm(id));
    }


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profile) {
        ProfileDTO dto = profileService.create(profile);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> update(@PathVariable String email,
                                    @RequestHeader("Authorization") String token){
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_ADMIN))
            return ResponseEntity.ok(profileService.update(email));
        else return ResponseEntity.status(403).build();
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<?> userUpdate(@PathVariable String email, @RequestBody ProfileDTO profile,
                                        @RequestHeader("Authorization") String token) {
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_ADMIN)) {

        }
        ProfileEntity profileEntity = profileService.userUpdate(email, profile);
        return ResponseEntity.ok(profileEntity);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token) {
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_ADMIN)){
        List<ProfileDTO> list = profileService.getAll();
        return ResponseEntity.ok(list);}
        else return ResponseEntity.status(403).build();
    }


    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
