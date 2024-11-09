package com.example.kun_uz.Profile.repository;

import com.example.kun_uz.Profile.entity.EmailHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity,Integer> {

    EmailHistoryEntity findByEmail(String email);


}
