package com.example.kun_uz.Article.dto;

import com.example.kun_uz.Article.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

public class ArticleDTO {
    private UUID id;
    private String title;
    private String description;
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
