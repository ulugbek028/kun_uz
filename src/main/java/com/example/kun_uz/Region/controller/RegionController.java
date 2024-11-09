package com.example.kun_uz.Region.controller;


import com.example.kun_uz.ExceptionHandler.AppBadException;
import com.example.kun_uz.Region.dto.RegionDTO;
import com.example.kun_uz.Region.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody RegionDTO region) {
        RegionDTO dto = regionService.create(region);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody RegionDTO region) {
        RegionDTO dto = regionService.update(id, region);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        regionService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<RegionDTO> dtos = regionService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
