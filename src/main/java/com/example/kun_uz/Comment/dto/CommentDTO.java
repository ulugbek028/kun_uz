package com.example.kun_uz.Comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class CommentDTO {
    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Integer profileId;
    private String content;
    private String articleId;
    private String replyId;
    private Boolean visible;
}
