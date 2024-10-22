package com.example.kun_uz.Article.entity;

import com.example.kun_uz.Article.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

@Entity
//@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Field must have some title!")
    private String title;

    @NotBlank(message = "Field must have some description!")
    private String description;

    @NotBlank(message = "Field must have some content!")
    private String content;
    private Integer shared_count;
    private String image_id;
    private Integer region_id;
    private Integer category_id;
    private Integer moderator_id;
    private Integer publisher_id;
    private Status status;
    private LocalDateTime created_date;
    private LocalDateTime published_date;
    private Boolean visible;
    private Integer view_count;

}


