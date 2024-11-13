package com.example.kun_uz.Article.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "article_type")
public class ArticleTypesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "article_id")
    private String articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", updatable = false, insertable = false)
    private ArticleEntity article;


    @Column(name = "article_type_id")
    private Integer articleTypeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_type_id", updatable = false, insertable = false)
    private ArticleTypeEntity articleType; // SectionEntity
}
