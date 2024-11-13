package com.example.kun_uz.Profile.entity;

import com.example.kun_uz.Attach.entity.AttachEntity;
import com.example.kun_uz.Enum.ProfileRole;
import com.example.kun_uz.Enum.ProfileStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "photo_id")
    private String photoId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity photo;
}
