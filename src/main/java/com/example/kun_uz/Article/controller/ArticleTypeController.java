package com.example.kun_uz.Article.controller;

import com.example.kun_uz.Article.dto.ArticleTypeDTO;
import com.example.kun_uz.Article.service.ArticleTypeService;
import com.example.kun_uz.ExceptionHandler.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> create(@RequestBody ArticleTypeDTO articleTypeDTO) {
        ArticleTypeDTO dto = articleTypeService.create(articleTypeDTO);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ArticleTypeDTO articleTypeDTO) {
        ArticleTypeDTO dto = articleTypeService.update(id, articleTypeDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> delete(@PathVariable Integer id) {
        articleTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> getAll() {
        List<ArticleTypeDTO> dtos = articleTypeService.getAll();
        return ResponseEntity.ok(dtos);
    }

    // lang



    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
