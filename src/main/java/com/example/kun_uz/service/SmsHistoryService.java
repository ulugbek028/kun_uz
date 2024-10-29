package com.example.kun_uz.service;

import com.example.kun_uz.entity.SmsHistoryEntity;
import com.example.kun_uz.repository.SmsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SmsHistoryService {
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    public void addHistoryCode(String phone, String code, LocalDateTime dateTime) {
        SmsHistoryEntity smsHistoryEntity = new SmsHistoryEntity();
        smsHistoryEntity.setPhone(phone);
        smsHistoryEntity.setCode(code);
        smsHistoryEntity.setCreatedDate(dateTime);
        smsHistoryRepository.save(smsHistoryEntity);


    }
}
