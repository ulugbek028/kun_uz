package com.example.kun_uz.Article.repository;

import com.example.kun_uz.Article.entity.ArticleTypesEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleTypesRepository extends CrudRepository<ArticleTypesEntity, String> {

    @Query("select articleTypeId from ArticleTypesEntity where articleId = ?1")
    List<Integer> findAllByArticleId(String articleId);


    @Transactional
    @Modifying
    @Query("delete  from ArticleTypesEntity where articleId = ?1 and articleTypeId =?2 ")
    void deleteByArticleIdAndArticleTypeId(String articleId, Integer articleTypeId);
}
