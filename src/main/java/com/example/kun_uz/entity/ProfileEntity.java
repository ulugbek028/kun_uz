package com.example.kun_uz.entity;

import com.example.kun_uz.Enum.ProfileRole;
import com.example.kun_uz.Enum.ProfileStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Field must have some name!")
    private String name;

    @NotBlank(message = "Field must have some surname!")
    private String surname;

    @NotBlank(message = "Field must have some email!")
    private String email;

    //    private Integer phone;

    @NotBlank
    private String password;

    @Enumerated(value = EnumType.STRING)
    private ProfileStatus status;

    @Enumerated(value = EnumType.STRING)
    private ProfileRole role;
    private Boolean visible;
    private LocalDateTime createdDate;
    private String photoId;
}
