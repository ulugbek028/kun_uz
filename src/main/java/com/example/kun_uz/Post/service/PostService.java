package com.example.kun_uz.Post.service;

import com.example.kun_uz.Post.dto.AttachDTO;
import com.example.kun_uz.Post.dto.PostDTO;
import com.example.kun_uz.Post.entity.PostEntity;
import com.example.kun_uz.Post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostAttachService postAttachService;

    public PostDTO createPost(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setContent(postDTO.getContent());

        postRepository.save(postEntity);

        postAttachService.create(postEntity.getId(), postDTO.getAttachList());

        postDTO.setId(postEntity.getId());
        return postDTO;
    }

    public PostDTO getById(Integer id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Post not found");
        });

        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        // attach list
        List<AttachDTO> attachList = postAttachService.getAttachList(id);
        postDTO.setAttachList(attachList);
        return postDTO;
    }

    public PostDTO updatePost(Integer id, PostDTO postDTO) {
        PostEntity entity = postRepository.findById(id).orElse(null);

        entity.setTitle(postDTO.getTitle());
        entity.setContent(postDTO.getContent());
        postRepository.save(entity);

        postAttachService.create(id, postDTO.getAttachList());

        postDTO.setId(id);
        return postDTO;
    }



}
