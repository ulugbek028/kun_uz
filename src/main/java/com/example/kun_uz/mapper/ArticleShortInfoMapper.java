package com.example.kun_uz.mapper;

import java.time.LocalDateTime;

public interface ArticleShortInfoMapper {

    String getId();

    String getTitle();

    String getDescription();

    String getImageId();

    LocalDateTime getPublishDate();


}
