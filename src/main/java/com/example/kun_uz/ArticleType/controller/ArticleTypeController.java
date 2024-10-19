package com.example.kun_uz.ArticleType.controller;

import com.example.kun_uz.ArticleType.dto.ArticleTypeDTO;
import com.example.kun_uz.ArticleType.service.ArticleTypeService;
import com.example.kun_uz.ExceptionHandler.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping("")
    private ResponseEntity<?> create(@RequestBody ArticleTypeDTO articleTypeDTO) {
        ArticleTypeDTO dto = articleTypeService.create(articleTypeDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ArticleTypeDTO articleTypeDTO) {
        ArticleTypeDTO dto = articleTypeService.update(id, articleTypeDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Integer id) {
        articleTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    private ResponseEntity<?> getAll() {
        List<ArticleTypeDTO> dtos = articleTypeService.getAll();
        return ResponseEntity.ok(dtos);
    }



    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
