package com.example.kun_uz.Article.controller;

import com.example.kun_uz.Article.dto.ArticleTypeDTO;
import com.example.kun_uz.Article.service.ArticleTypeService;
import com.example.kun_uz.Enum.LanguageEnum;
import com.example.kun_uz.ExceptionHandler.AppBadException;
import com.example.kun_uz.mapper.ArticleTypeInfoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article-type")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping("")
    public ResponseEntity<ArticleTypeDTO> create(@Valid @RequestBody ArticleTypeDTO articleTypeDTO) {
        return ResponseEntity.ok(articleTypeService.create(articleTypeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleTypeDTO> update(@PathVariable("id") Integer id,
                                                 @Valid @RequestBody ArticleTypeDTO articleTypeDTO) {
        return ResponseEntity.ok(articleTypeService.update(id, articleTypeDTO));
    }

    @GetMapping("")
    public ResponseEntity<Page<ArticleTypeDTO>> update(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(articleTypeService.getAll(page - 1, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleTypeService.delete(id));
    }

    @GetMapping("/lang")
    public ResponseEntity<List<ArticleTypeInfoMapper>> update(
            @RequestHeader(value = "Accept-language", defaultValue = "uz") LanguageEnum lang) {
        return ResponseEntity.ok(articleTypeService.getAllByLang(lang));
    }


    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
