package com.example.kun_uz.Post.controller;

import com.example.kun_uz.Post.dto.PostDTO;
import com.example.kun_uz.Post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("")
    public ResponseEntity<?> post(@RequestBody PostDTO postDTO) {
        PostDTO dto = postService.createPost(postDTO);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public PostDTO byId(@PathVariable("id") Integer id) {
        PostDTO result = postService.getById(id);
        return result;
    }

    @PutMapping("/update/{id}")
    public PostDTO update(@PathVariable("id") Integer id, @RequestBody PostDTO postDTO) {
        PostDTO dto = postService.updatePost(id, postDTO);
        return dto;
    }


}
