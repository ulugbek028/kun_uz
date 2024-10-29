package com.example.kun_uz.repository;

import com.example.kun_uz.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>, PagingAndSortingRepository<CategoryEntity, Integer> {



}
