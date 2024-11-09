package com.example.kun_uz.Profile.controller;

import com.example.kun_uz.Enum.ProfileRole;
import com.example.kun_uz.ExceptionHandler.AppBadException;
import com.example.kun_uz.Profile.dto.JwtDTO;
import com.example.kun_uz.Profile.dto.ProfileDTO;
import com.example.kun_uz.Profile.dto.RegistrationDTO;
import com.example.kun_uz.Profile.dto.UpdateProfileDetailDTO;
import com.example.kun_uz.Profile.entity.ProfileEntity;
import com.example.kun_uz.Profile.service.AuthService;
import com.example.kun_uz.Profile.service.ProfileService;
import com.example.kun_uz.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profile) {
        ProfileDTO dto = profileService.create(profile);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/admin/{email}")
    public ResponseEntity<?> update(@PathVariable String email,
                                    @RequestHeader("Authorization") String token){
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_ADMIN))
            return ResponseEntity.ok(profileService.update(email));
        else return ResponseEntity.status(403).build();
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<?> userUpdate(@PathVariable String email, @RequestBody ProfileDTO profile) {
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


    @PutMapping("/detail")
    public ResponseEntity<Boolean> updateDetail(@RequestBody @Valid UpdateProfileDetailDTO requestDTO,
                                                @RequestHeader("Authorization") String token) {
        JwtDTO dto = JwtUtil.decode(token.substring(7));
        return ResponseEntity.ok().body(profileService.updateDetail(requestDTO, dto.getUsername()));
    }



    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
