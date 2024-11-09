package com.example.kun_uz.Post.repository;

import com.example.kun_uz.Post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Integer> {
}
