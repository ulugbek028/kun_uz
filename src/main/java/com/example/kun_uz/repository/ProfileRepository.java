package com.example.kun_uz.repository;

import com.example.kun_uz.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer>, PagingAndSortingRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);

    Optional<ProfileEntity> findByIdAndVisibleTrue(Integer id);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM ProfileEntity a WHERE a.email = ?1 AND a.status = 'IN_REGISTRATION'")
    Boolean existsByUsername(String email);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity s SET s.status = 'ACTIVE' WHERE s.email=?1 ")
    Integer updateStatus(String email);





}
