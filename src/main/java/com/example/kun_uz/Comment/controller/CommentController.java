package com.example.kun_uz.Comment.controller;

import com.example.kun_uz.Comment.dto.CommentDTO;
import com.example.kun_uz.Comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{aticle_id}")
    public ResponseEntity<?> create(@PathVariable String aticle_id,
                                    @RequestBody CommentDTO commentDTO) {
        CommentDTO dto = commentService.create(aticle_id,commentDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{article_id}")
    public ResponseEntity<?> update(@PathVariable String article_id,
                                    @RequestBody CommentDTO commentDTO) {
        CommentDTO dto = commentService.update(article_id,commentDTO);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ADMIN') or ")
    @DeleteMapping("/{article_id}")
    public ResponseEntity<?> delete(@PathVariable String article_id) {
        commentService.delete(article_id);
        return ResponseEntity.ok().build();
    }
}
