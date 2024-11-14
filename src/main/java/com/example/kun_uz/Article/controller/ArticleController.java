package com.example.kun_uz.Article.controller;

import com.example.kun_uz.Article.dto.ArticleDTO;
import com.example.kun_uz.Article.dto.ArticleShortInfo;
import com.example.kun_uz.Article.service.ArticleService;
import com.example.kun_uz.ExceptionHandler.AppBadException;
import com.example.kun_uz.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ArticleDTO articleDTO
                                    /*@RequestHeader("Authorization") String token*/) {
        String token = "";
       ArticleDTO dto = articleService.create(articleDTO,token);
       return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> byId(@PathVariable("id") String id, HttpServletRequest request) {
        return ResponseEntity.ok(articleService.getById(id, HeaderUtil.getUserIP(request)));
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody ArticleDTO articleDTO){
        ArticleDTO dto = articleService.update(id,articleDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        articleService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> statusById(@PathVariable String id){
        articleService.statusById(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/get5")
    public ResponseEntity<?> get5(@RequestBody List<String> excludeIdList){
        List<ArticleShortInfo> last5 = articleService.getLast5(excludeIdList);
        return ResponseEntity.ok(last5);
    }

    // Region id
    @GetMapping("/region/{id}")
    public ResponseEntity<?> getArticleRegion(@PathVariable Integer id,
                                              @RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<ArticleShortInfo> articleShortInfoDTO = articleService.ByRegionId(id,page - 1,size);
        return ResponseEntity.ok(articleShortInfoDTO);
    }

    /*// Category id
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getArticleRegion(@PathVariable Integer id,
                                              @RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        ArticleShortInfo articleShortInfoDTO = articleService.ByCategoryId(id, page - 1, size);
        return ResponseEntity.ok(articleShortInfoDTO);

    }*/

        @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
