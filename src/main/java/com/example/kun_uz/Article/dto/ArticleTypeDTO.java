package com.example.kun_uz.Article.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleTypeDTO {
    private Integer id;

    @NotNull(message = "OrderNumber required")
    private Integer orderNumber;
    @NotBlank(message = "NameUz required")
    private String nameUz;
    @NotBlank(message = "NameEn required")
    private String nameEn;
    @NotBlank(message = "NameRu required")
    private String nameRu;

    private String name;
    private LocalDateTime createdDate;
}
