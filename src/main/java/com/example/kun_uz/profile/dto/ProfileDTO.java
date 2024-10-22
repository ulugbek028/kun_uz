package com.example.kun_uz.profile.dto;


import com.example.kun_uz.profile.enums.ProfileRole;
import com.example.kun_uz.profile.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProfileDTO {
    private UUID id;
    private String name;
    private String surname;
    private String email;
//    private Integer phone;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private Boolean visible;
    private LocalDateTime created_date;
    private String photo_id;

}
