package com.example.kun_uz.Post.repository;

import com.example.kun_uz.Post.entity.PostAttachEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostAttachRepository extends JpaRepository<PostAttachEntity, Integer> {

    @Query("select attachId from PostAttachEntity where postId =?1")
    List<String> findAllByPostId(Integer postId);

    @Modifying
    @Transactional
    @Query("delete from PostAttachEntity where postId =?1")
    void deleteByPostId(Integer postId);

    @Modifying
    @Transactional
    @Query("delete from PostAttachEntity where postId =?1 and attachId = ?2")
    void deleteByPostIdAndAttachId(Integer postId, String attachId);






}
