package com.example.kun_uz.Category.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;
    @Column(unique = true)
    private Integer order_number;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private Boolean visible;
    private LocalDateTime created_date;
}
