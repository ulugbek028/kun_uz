package com.example.kun_uz.Article.service;

import com.example.kun_uz.Article.Status;
import com.example.kun_uz.Article.dto.ArticleDTO;
import com.example.kun_uz.Article.entity.ArticleEntity;
import com.example.kun_uz.Article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleDTO create(ArticleDTO articleDTO) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(articleDTO.getTitle());
        articleEntity.setDescription(articleDTO.getDescription());
        articleEntity.setContent(articleDTO.getContent());
        articleEntity.setStatus(Status.NotPublished);
        articleEntity.setImage_id(articleDTO.getImage_id());
        articleEntity.setCategory_id(articleDTO.getCategory_id());
        articleEntity.setRegion_id(articleDTO.getRegion_id());
//        articleEntity.setAr
        articleEntity.setCreated_date(LocalDateTime.now());

        articleRepository.save(articleEntity);

        articleDTO.setId(articleEntity.getId());
        return articleDTO;
    }


}
