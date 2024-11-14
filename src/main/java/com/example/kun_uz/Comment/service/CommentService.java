package com.example.kun_uz.Comment.service;

import com.example.kun_uz.Article.entity.ArticleEntity;
import com.example.kun_uz.Article.repository.ArticleRepository;
import com.example.kun_uz.Comment.dto.CommentDTO;
import com.example.kun_uz.Comment.entity.CommentEntity;
import com.example.kun_uz.Comment.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

//    content,article_id,reply_id

    public CommentDTO create(String articleId, CommentDTO commentDTO) {

        Optional<ArticleEntity> articleEntity = articleRepository.findById(articleId);
        CommentEntity entity = new CommentEntity();
        entity.setArticleId(articleId);
        entity.setContent(commentDTO.getContent());
//        entity.setReplyId(commentDTO.getReplyId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(Boolean.TRUE);

        commentRepository.save(entity);
        commentDTO.setId(entity.getId());
        commentDTO.setCreatedDate(entity.getCreatedDate());
        return commentDTO;
    }

    public CommentDTO update(String articleId, CommentDTO commentDTO) {
        Optional<CommentEntity> commentEntity = commentRepository.findByArticleId(articleId);
        CommentEntity entity = commentEntity.get();
        entity.setContent(commentDTO.getContent());
        entity.setUpdateDate(LocalDateTime.now());
        commentRepository.save(entity);
        return commentDTO;
    }

    public void delete(String articleId) {
        Optional<CommentEntity> commentEntity = commentRepository.findByArticleId(articleId);
        CommentEntity entity = commentEntity.get();
        entity.setVisible(Boolean.FALSE);
    }
}
