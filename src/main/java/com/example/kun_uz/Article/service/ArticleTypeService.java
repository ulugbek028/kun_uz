package com.example.kun_uz.Article.service;

import com.example.kun_uz.Article.dto.ArticleTypeDTO;
import com.example.kun_uz.Article.entity.ArticleTypeEntity;
import com.example.kun_uz.Article.repository.ArticleTypeRepository;
import com.example.kun_uz.Enum.LanguageEnum;
import com.example.kun_uz.mapper.ArticleTypeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository repository;

    public ArticleTypeDTO create(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(Boolean.TRUE);
        repository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Page<ArticleTypeDTO> getAll(int page, int sie) {
        Pageable pageable = PageRequest.of(page, sie);
        Page<ArticleTypeEntity> result = repository.findAllByVisibleTrue(pageable);

        Long totalCount = result.getTotalElements();

        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        for (ArticleTypeEntity entity : result.getContent()) {
            dtoList.add(mapToDTO(entity));
        }
        return new PageImpl<>(dtoList, pageable, totalCount);
    }

    public ArticleTypeDTO update(Integer id, ArticleTypeDTO dto) {
        ArticleTypeEntity entity = get(id);
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setOrderNumber(dto.getOrderNumber());
        repository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    public Boolean delete(Integer id) {
        int result = repository.changeVisible(id);
        return result > 0;
//        ArticleTypeEntity entity = get(id);
//        entity.setVisible(Boolean.FALSE);
//        repository.save(entity);
//        return true;
    }

    public List<ArticleTypeInfoMapper> getAllByLang(LanguageEnum lang) {
        List<ArticleTypeInfoMapper> result = repository.getByLang(lang.name());
        return result;

//        List<ArticleTypeDTO> dtoList = new LinkedList<>();
//
//        for(ArticleTypeInfoMapper mapper : result){
//            ArticleTypeDTO dto = new ArticleTypeDTO();
//            dto.setId(mapper.getId());
//            dto.setOrderNumber(mapper.getOrderNumber());
//            dto.setName(mapper.getName());
//            dtoList.add(dto);
//        }
//        return dtoList;


//        List<ArticleTypeEntity> result = repository.findAllByVisibleTrue();

//        List<ArticleTypeDTO> dtoList = new LinkedList<>();
//        for (ArticleTypeEntity entity : result) {
//            ArticleTypeDTO dto = new ArticleTypeDTO();
//            dto.setId(entity.getId());
//            dto.setOrderNumber(entity.getOrderNumber());
//            switch (lang){
//                case en -> dto.setName(entity.getNameEn());
//                case uz -> dto.setName(entity.getNameUz());
//                case ru -> dto.setName(entity.getNameRu());
//            }
//            dtoList.add(dto);
//        }
//        return dtoList;
    }

    public ArticleTypeEntity get(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("ArticleType Not Found");
        });
    }

    public ArticleTypeDTO mapToDTO(ArticleTypeEntity entity) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }


}
