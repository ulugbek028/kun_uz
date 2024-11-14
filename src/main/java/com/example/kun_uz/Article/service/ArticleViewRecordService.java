package com.example.kun_uz.Article.service;

import com.example.kun_uz.Article.entity.ArticleViewRecordEntity;
import com.example.kun_uz.Article.repository.ArticleViewRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ArticleViewRecordService {

    private final ArticleViewRecordRepository articleViewRecordRepository;

    // TODO
    // 1. Trigger
    // 2. Function bilan
    public boolean increaseViewCount(String articleId, String ip) {
        boolean result = articleViewRecordRepository.existsByArticleIdAndIpAddress(articleId, ip);
        if (!result) {
            ArticleViewRecordEntity entity = new ArticleViewRecordEntity();
            entity.setArticleId(articleId);
            entity.setIpAddress(ip);
            entity.setCreatedDate(LocalDateTime.now());
            articleViewRecordRepository.save(entity);
            return true;
        }
        return false;
    }
}
