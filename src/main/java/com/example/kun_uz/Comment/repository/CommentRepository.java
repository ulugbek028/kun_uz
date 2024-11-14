package com.example.kun_uz.Comment.repository;

import com.example.kun_uz.Comment.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, String> {

    Optional<CommentEntity> findByArticleId(String articleId);
}
