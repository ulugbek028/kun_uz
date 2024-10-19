package com.example.kun_uz.Category.controller;

import com.example.kun_uz.Category.dto.CategoryDTO;
import com.example.kun_uz.Category.service.CategoryService;
import com.example.kun_uz.ExceptionHandler.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    private ResponseEntity<?> create(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO dto = categoryService.create(categoryDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO dto = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    private ResponseEntity<?> getAll() {
        List<CategoryDTO> dtos = categoryService.getAll();
        return ResponseEntity.ok(dtos);
    }



    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
