package com.example.kun_uz.Article.repository;

import com.example.kun_uz.Article.entity.ArticleViewRecordEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArticleViewRecordRepository extends CrudRepository<ArticleViewRecordEntity, String> {

    boolean existsByArticleIdAndIpAddress(String articleId, String ip);

}
