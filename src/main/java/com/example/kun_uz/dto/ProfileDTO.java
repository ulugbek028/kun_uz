package com.example.kun_uz.dto;


import com.example.kun_uz.Enum.ProfileRole;
import com.example.kun_uz.Enum.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
//    private Integer phone;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private Boolean visible;
    private LocalDateTime createdDate;
    private String photoId;
    private String jwtToken;
}
