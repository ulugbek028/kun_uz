package com.example.kun_uz.Comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updateDate;
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "content")
    private String content;
    @Column(name = "article_id")
    private String articleId;
    @Column(name = "reply_id")
    private String replyId;
    @Column(name = "visible")
    private Boolean visible;
}
