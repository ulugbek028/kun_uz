package com.example.kun_uz.profile.controller;

import com.example.kun_uz.ExceptionHandler.AppBadException;
import com.example.kun_uz.profile.dto.ProfileDTO;
import com.example.kun_uz.profile.entity.ProfileEntity;
import com.example.kun_uz.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profile) {
        ProfileDTO dto = profileService.create(profile);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> update(@PathVariable String email) {
        ProfileEntity profileEntity = profileService.update(email);
        return ResponseEntity.ok(profileEntity);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<?> userUpdate(@PathVariable String email, @RequestBody ProfileDTO profile) {
        ProfileEntity profileEntity = profileService.userUpdate(email,profile);
        return ResponseEntity.ok(profileEntity);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<ProfileDTO> list = profileService.getAll();
        return ResponseEntity.ok(list);
    }



    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
