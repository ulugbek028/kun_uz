package com.example.kun_uz.profile.entity;

import com.example.kun_uz.profile.enums.ProfileRole;
import com.example.kun_uz.profile.enums.ProfileStatus;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Field must have some name!")
    private String name;

    @NotBlank(message = "Field must have some surname!")
    private String surname;

    @NotBlank(message = "Field must have some email!")
    @Email(message = "Email should be valid!")
    private String email;

    //    private Integer phone;

    @NotBlank
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private Boolean visible;
    private LocalDateTime created_date;
    private String photo_id;
}
