package com.example.kun_uz.Article.repository;

import com.example.kun_uz.Article.entity.ArticleEntity;
import com.example.kun_uz.Enum.ArticleStatus;
import com.example.kun_uz.mapper.ArticleShortInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, String>, PagingAndSortingRepository<ArticleEntity,String> {

    Optional<ArticleEntity> findById(String id);

    @Query(" select a.id as id,  a.title as title, a.description as description, a.imageId as imageId, a.publishedDate as publishedDate " +
            "  From ArticleEntity a  where  a.id not in ?2 and a.status =?1 and a.visible = true order by a.createdDate desc")
    List<ArticleShortInfoMapper> getLastIdNotIn(ArticleStatus status,
                                                List<String> excludeIdList, Pageable pageable);

//    Optional<ArticleEntity> findByRegionId(Integer id);

    @Query(" From ArticleEntity a where a.regionId = ?1  ")
    Page<ArticleEntity> getRegion(Integer id, Pageable pageable);
}
