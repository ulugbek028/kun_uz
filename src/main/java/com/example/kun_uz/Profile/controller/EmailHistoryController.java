package com.example.kun_uz.Profile.controller;

import com.example.kun_uz.Profile.dto.EmailHistoryDTO;
import com.example.kun_uz.Profile.service.EmailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emailHistory")
public class EmailHistoryController {

    @Autowired
    public EmailHistoryService emailHistoryService;

    @GetMapping("/email/{email}")
    ResponseEntity<?> emailHistory(@PathVariable String email) {
        EmailHistoryDTO emailHistoryDTO = emailHistoryService.getByEmail(email);
        return ResponseEntity.ok(emailHistoryDTO);
    }
}
