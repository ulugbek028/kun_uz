package com.example.kun_uz.ArticleType.service;

import com.example.kun_uz.ArticleType.dto.ArticleTypeDTO;
import com.example.kun_uz.ArticleType.entity.ArticleTypeEntity;
import com.example.kun_uz.ArticleType.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ArticleTypeService {

    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public ArticleTypeDTO create(ArticleTypeDTO articleTypeDTO) {
        ArticleTypeEntity dto = new ArticleTypeEntity();
        dto.setOrder_number(articleTypeDTO.getOrder_number());
        dto.setNameUz(articleTypeDTO.getNameUz());
        dto.setNameEn(articleTypeDTO.getNameEn());
        dto.setNameRu(articleTypeDTO.getNameRu());
        dto.setCreatedDate(LocalDateTime.now());

        articleTypeRepository.save(dto);

        articleTypeDTO.setId(dto.getId());
        return articleTypeDTO;
    }

    public ArticleTypeDTO update(Integer id, ArticleTypeDTO dto) {

        ArticleTypeEntity dtoEntity = articleTypeRepository.getById(id);
        dtoEntity.setOrder_number(dto.getOrder_number());
        dtoEntity.setNameUz(dto.getNameUz());
        dtoEntity.setNameEn(dto.getNameEn());
        dtoEntity.setNameRu(dto.getNameRu());
        dtoEntity.setCreatedDate(LocalDateTime.now());

        articleTypeRepository.save(dtoEntity);

        dto.setId(dtoEntity.getId());
        return dto;
    }

    public void delete(Integer id) {
        ArticleTypeEntity dto = articleTypeRepository.getById(id);
        dto.setVisible(Boolean.FALSE);
        articleTypeRepository.save(dto);
    }

    public List<ArticleTypeDTO> getAll() {
        List<ArticleTypeEntity> articleList = articleTypeRepository.findAll();
        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        for (ArticleTypeEntity entity : articleList) {
            ArticleTypeDTO dto = new ArticleTypeDTO();
            dto.setId(entity.getId());
            dto.setOrder_number(entity.getOrder_number());
            dto.setNameUz(entity.getNameUz());
            dto.setNameEn(entity.getNameEn());
            dto.setNameRu(entity.getNameRu());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }


}
