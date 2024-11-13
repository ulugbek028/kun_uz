package com.example.kun_uz.Article.service;

import com.example.kun_uz.Article.dto.ArticleDTO;
import com.example.kun_uz.Article.dto.ArticleShortInfo;
import com.example.kun_uz.Article.entity.ArticleEntity;
import com.example.kun_uz.Article.repository.ArticleRepository;
import com.example.kun_uz.Attach.service.AttachService;
import com.example.kun_uz.Enum.ArticleStatus;
import com.example.kun_uz.Profile.dto.JwtDTO;
import com.example.kun_uz.Profile.entity.ProfileEntity;
import com.example.kun_uz.Profile.service.ProfileService;
import com.example.kun_uz.mapper.ArticleShortInfoMapper;
import com.example.kun_uz.util.JwtUtil;
import com.example.kun_uz.util.SpringSecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleTypesService articleTypesService;
    private final AttachService attachService;
    @Autowired
    private ProfileService profileService;

    public ArticleDTO create(ArticleDTO dto, String token) {
//        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
//        ProfileEntity profile = profileService.getByUsername(jwtDTO.getUsername());
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setDescription(dto.getDescription());
        entity.setImageId(dto.getImageId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setSharedCount(0);
        entity.setViewCount(0);
        entity.setStatus(ArticleStatus.NOT_PULISHED);
        entity.setVisible(true);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setModeratorId(1);
        //
        articleRepository.save(entity);
        //
        articleTypesService.merge(entity.getId(), dto.getArticleTypesList());
        return dto;
    }

    public ArticleDTO update(String id, ArticleDTO dto) {
        Optional<ArticleEntity> entity = articleRepository.findById(id);
        ArticleEntity articleEntity = entity.get();
        articleEntity.setTitle(dto.getTitle());
        articleEntity.setContent(dto.getContent());
        articleEntity.setDescription(dto.getDescription());
        articleEntity.setImageId(dto.getImageId());
        articleEntity.setRegionId(dto.getRegionId());
        articleEntity.setCategoryId(dto.getCategoryId());

        articleRepository.save(articleEntity);

        return dto;
    }



    public void delete(String id) {
        Optional<ArticleEntity> entity = articleRepository.findById(id);
        ArticleEntity articleEntity = entity.get();
        articleEntity.setVisible(Boolean.FALSE);
    }

    public void statusById(String id) {
        Optional<ArticleEntity> entity = articleRepository.findById(id);
        ArticleEntity articleEntity = entity.get();
        articleEntity.setStatus(ArticleStatus.PUBLISHED);
    }

    public List<ArticleShortInfo> getLast5(List<String> excludeIdList) {
        List<ArticleShortInfoMapper> mapperList = articleRepository.getLastIdNotIn(ArticleStatus.PUBLISHED,
                excludeIdList,
                PageRequest.of(0, 5));
        return mapperList.stream().map(item -> toShortInfo(item)).toList();
    }

    public List<ArticleShortInfo> getLast3(List<String> excludeIdList) {
        List<ArticleShortInfoMapper> mapperList = articleRepository.getLastIdNotIn(ArticleStatus.PUBLISHED,
                excludeIdList,
                PageRequest.of(0, 3));
        return mapperList.stream().map(item -> toShortInfo(item)).toList();
    }

    public List<ArticleShortInfo> getLast8(List<String> excludeIdList) {
        List<ArticleShortInfoMapper> mapperList = articleRepository.getLastIdNotIn(ArticleStatus.PUBLISHED,
                excludeIdList,
                PageRequest.of(0, 8));
        return mapperList.stream().map(item -> toShortInfo(item)).toList();
    }

    public Page<ArticleShortInfo> ByRegionId(Integer id, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("created_date").descending());

        Page<ArticleEntity> entityList = articleRepository.getRegion(id,pageRequest);
        Long total = entityList.getTotalElements();
        List<ArticleShortInfo> dtoList = new LinkedList<>();
        for (ArticleEntity entity : entityList) {
            ArticleShortInfo dto = new ArticleShortInfo();
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());
            dto.setPublishedDate(entity.getPublishedDate());
            dtoList.add(dto);
        }
        PageImpl page1 = new PageImpl<>(dtoList, pageRequest, total);

        return page1;
    }


    public ArticleShortInfo toShortInfo(ArticleShortInfoMapper mapper) {
        ArticleShortInfo dto = new ArticleShortInfo();
        dto.setId(mapper.getId());
        dto.setTitle(mapper.getTitle());
        dto.setDescription(mapper.getDescription());
        dto.setPublishedDate(mapper.getPublishDate());
        dto.setImage(attachService.getDTO(mapper.getImageId()));
        return dto;
    }


}
