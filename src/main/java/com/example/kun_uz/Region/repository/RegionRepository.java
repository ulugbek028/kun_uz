package com.example.kun_uz.Region.repository;

import com.example.kun_uz.Region.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer>, PagingAndSortingRepository<RegionEntity, Integer> {

}
