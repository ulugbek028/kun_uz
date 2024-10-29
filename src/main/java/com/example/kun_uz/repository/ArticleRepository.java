package com.example.kun_uz.repository;

import com.example.kun_uz.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer>, PagingAndSortingRepository<ArticleEntity, Integer> {

}
