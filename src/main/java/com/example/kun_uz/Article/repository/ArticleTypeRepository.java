package com.example.kun_uz.Article.repository;

import com.example.kun_uz.Article.entity.ArticleEntity;
import com.example.kun_uz.Article.entity.ArticleTypeEntity;
import com.example.kun_uz.mapper.ArticleTypeInfoMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity, Integer>, PagingAndSortingRepository<ArticleTypeEntity,Integer>{
    //    Optional<ArticleTypeEntity> getById(Integer id);
    Page<ArticleTypeEntity> findAllByVisibleTrue(Pageable pageable);

    List<ArticleTypeEntity> findAllByVisibleTrue();

    @Modifying
    @Transactional
    @Query("update ArticleTypeEntity set visible = false  where id=?1")
    int changeVisible(Integer id);

    @Query(value = "select id as id , order_number as orderNumber, " +
            "       case ?1 " +
            "           when 'uz' then name_uz " +
            "           when 'en' then name_en " +
            "        else name_ru " +
            "        end as name " +
            "From article_type " +
            " where visible is true  " +
            " order by order_number;", nativeQuery = true)
    List<ArticleTypeInfoMapper> getByLang(String lang);

}
