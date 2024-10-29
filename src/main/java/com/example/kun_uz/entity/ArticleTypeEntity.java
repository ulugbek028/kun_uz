package com.example.kun_uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @Column(unique = true)
    private Integer order_number;

    private String nameUz;

    private String nameRu;

    private String nameEn;

    private Boolean visible = Boolean.TRUE;

    private LocalDateTime createdDate;

}
