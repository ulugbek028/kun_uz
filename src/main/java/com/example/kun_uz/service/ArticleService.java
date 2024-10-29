package com.example.kun_uz.service;

import com.example.kun_uz.dto.ArticleDTO;
import com.example.kun_uz.entity.ArticleEntity;
import com.example.kun_uz.Enum.Status;
import com.example.kun_uz.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleDTO create(ArticleDTO articleDTO) {
        ArticleEntity entity = new ArticleEntity();
        entity.setId(UUID.randomUUID());
        entity.setTitle(articleDTO.getTitle());
        entity.setContent(articleDTO.getContent());
        entity.setImage_id(articleDTO.getImage_id());
        entity.setRegion_id(articleDTO.getRegion_id());
        entity.setCategory_id(articleDTO.getCategory_id());
        entity.setCreated_date(LocalDateTime.now());

        entity.setStatus(Status.NotPublished);

        articleRepository.save(entity);

        articleDTO.setId(entity.getId());
        articleDTO.setCreated_date(entity.getCreated_date());
        return articleDTO;
    }
}
