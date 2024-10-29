package com.example.kun_uz.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomArticleTypeRepository {

    @Autowired
    private ArticleTypeRepository articleRepository;


}
