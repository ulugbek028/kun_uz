package com.example.kun_uz.Profile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@Entity
public class EmailHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "message")
    private String message;
    @Column(name = "email")
    private String email;
    @Column(name = "created_data")
    private LocalDateTime createdData;
}
