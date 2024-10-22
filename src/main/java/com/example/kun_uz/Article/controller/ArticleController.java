package com.example.kun_uz.Article.controller;

import com.example.kun_uz.Article.dto.ArticleDTO;
import com.example.kun_uz.Article.service.ArticleService;
import com.example.kun_uz.ExceptionHandler.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articlies")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ArticleDTO articleDTO) {
       ArticleDTO dto = articleService.create(articleDTO);
       return ResponseEntity.ok(dto);
    }



    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
