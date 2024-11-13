package com.example.kun_uz.Post.service;

import com.example.kun_uz.Attach.dto.AttachDTO;
import com.example.kun_uz.Attach.service.AttachService;
import com.example.kun_uz.Post.entity.PostAttachEntity;
import com.example.kun_uz.Post.repository.PostAttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostAttachService {
    @Autowired
    private PostAttachRepository postAttachRepository;

    @Autowired
    private AttachService attachService;

    public void create(Integer postId, List<AttachDTO> attachIdList) {
        if( attachIdList == null){
            return;
        }
        for (AttachDTO attachId : attachIdList) {
            PostAttachEntity entity = new PostAttachEntity();
            entity.setPostId(postId);
            entity.setAttachId(attachId.getId());
            postAttachRepository.save(entity);
        }
    }

    public List<AttachDTO> getAttachList(Integer postId) {
        List<String> attachIdList = postAttachRepository.findAllByPostId(postId);
        List<AttachDTO> attachDTOList = new ArrayList<>();
        for (String attachId : attachIdList) {
            attachDTOList.add(attachService.getDTO(attachId));
        }
        return attachDTOList;
    }

    /*public void update(Integer postId, List<AttachDTO> attachIdList) {
        if( attachIdList == null){
            return;
        }
        for (AttachDTO attachId : attachIdList) {
            PostAttachEntity entity = new PostAttachEntity();
            entity.setPostId(postId);
            entity.setAttachId(attachId.getId());
            postAttachRepository.save(entity);
        }
    }*/


}
