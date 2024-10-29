package com.example.kun_uz.repository;

import com.example.kun_uz.entity.SmsHistoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SmsHistoryRepository extends CrudRepository<SmsHistoryEntity, String> {

    // 1231231
    @Query("Select count (s) from SmsHistoryEntity s where s.phone = ?1 and s.createdDate between ?2 and ?3 ")
    Long getSmsCount(String phone, LocalDateTime from, LocalDateTime to);

    Optional<SmsHistoryEntity> findTopByPhoneOrderByCreatedDateDesc(String phone);

    @Modifying
    @Transactional
    @Query("update SmsHistoryEntity set attemptCount = attemptCount + 1 where id = ?1")
    void increaseAttemptCount(String id);

    @Query("SELECT c FROM SmsHistoryEntity c WHERE c.phone = ?1 ORDER BY c.createdDate DESC")
    SmsHistoryEntity findLatestCodeByPhone(String phone);

}
