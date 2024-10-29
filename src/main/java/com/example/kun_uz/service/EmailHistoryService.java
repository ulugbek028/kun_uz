package com.example.kun_uz.service;

import com.example.kun_uz.dto.EmailHistoryDTO;
import com.example.kun_uz.entity.EmailHistoryEntity;
import com.example.kun_uz.repository.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailHistoryService {

    @Autowired
    EmailHistoryRepository emailHistoryRepository;

    public EmailHistoryDTO create(EmailHistoryDTO emailHistoryDTO) {
        EmailHistoryEntity entity = new EmailHistoryEntity();
        entity.setEmail(emailHistoryDTO.getEmail());
        entity.setMessage(emailHistoryDTO.getMessage());
        entity.setCreatedData(emailHistoryDTO.getCreated_data());

        emailHistoryRepository.save(entity);

        emailHistoryDTO.setId(entity.getId());
        return emailHistoryDTO;
    }


    public EmailHistoryDTO getByEmail(String email) {
        EmailHistoryEntity entity = emailHistoryRepository.findByEmail(email);

        EmailHistoryDTO emailHistoryDTO = new EmailHistoryDTO();
        emailHistoryDTO.setId(entity.getId());
        emailHistoryDTO.setEmail(entity.getEmail());
        emailHistoryDTO.setMessage(entity.getMessage());
        emailHistoryDTO.setCreated_data(entity.getCreatedData());
        return emailHistoryDTO;
    }
}
