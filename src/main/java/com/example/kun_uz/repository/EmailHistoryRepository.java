package com.example.kun_uz.repository;

import com.example.kun_uz.entity.EmailHistoryEntity;
import com.example.kun_uz.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity,Integer> {

    EmailHistoryEntity findByEmail(String email);


}
