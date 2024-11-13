package com.example.kun_uz.Article.service;

import com.example.kun_uz.Article.entity.ArticleTypesEntity;
import com.example.kun_uz.Article.repository.ArticleTypesRepository;
import com.example.kun_uz.Attach.dto.AttachDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ArticleTypesService {

    private final ArticleTypesRepository articleTypesRepository;


    public void merge(String articleId, List<Integer> newIdList) {
        List<Integer> oldIdList = articleTypesRepository.findAllByArticleId(articleId);

        if (newIdList == null) {
            newIdList = new ArrayList<>();
        }
        for (Integer attachId : oldIdList) {
            if (!newIdList.contains(attachId)) {
                // delete operation {attachId}
                articleTypesRepository.deleteByArticleIdAndArticleTypeId(articleId, attachId);
            }
        }

        for (Integer newItemId : newIdList) {
            if (!oldIdList.contains(newItemId)) {
                // save
                ArticleTypesEntity entity = new ArticleTypesEntity();
                entity.setArticleId(articleId);
                entity.setArticleTypeId(newItemId);
                articleTypesRepository.save(entity);
            }
        }
    }

    private boolean exists(String attachId, List<AttachDTO> dtoList) {
        for (AttachDTO dto : dtoList) {
            if (dto.getId().equals(attachId)) {
                return true;
            }
        }
        return false;
    }


}
