package com.example.kun_uz.ArticleType.repository;

import com.example.kun_uz.ArticleType.entity.ArticleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleTypeRepository extends JpaRepository<ArticleTypeEntity, Integer>, PagingAndSortingRepository<ArticleTypeEntity, Integer> {

    ArticleTypeEntity getById(Integer id);
}
