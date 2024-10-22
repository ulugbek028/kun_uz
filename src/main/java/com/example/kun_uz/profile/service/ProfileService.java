package com.example.kun_uz.profile.service;

import com.example.kun_uz.profile.dto.ProfileDTO;
import com.example.kun_uz.profile.entity.ProfileEntity;
import com.example.kun_uz.profile.enums.ProfileRole;
import com.example.kun_uz.profile.enums.ProfileStatus;
import com.example.kun_uz.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO create(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(UUID.randomUUID());
        profileEntity.setName(profileDTO.getName());
        profileEntity.setSurname(profileDTO.getSurname());
        profileEntity.setEmail(profileDTO.getEmail());
        profileEntity.setPassword(profileDTO.getPassword());
        profileEntity.setVisible(Boolean.TRUE);
        profileEntity.setRole(ProfileRole.ROLE_USER);
        profileEntity.setCreated_date(LocalDateTime.now());

        profileRepository.save(profileEntity);

        profileDTO.setId(profileEntity.getId());
        profileDTO.setCreated_date(profileEntity.getCreated_date());
        return profileDTO;
    }

    public ProfileEntity update(String email) {
        ProfileEntity profileEntity = profileRepository.findByEmail(email);
        profileEntity.setStatus(ProfileStatus.ACTIVE);
        profileEntity.setRole(ProfileRole.ROLE_USER);
        profileEntity.setPhoto_id("photo_id");
        profileEntity.setVisible(Boolean.TRUE);
        profileRepository.save(profileEntity);

        return profileEntity;
    }

    public ProfileEntity userUpdate(String email, ProfileDTO profileDTO) {
        ProfileEntity profileEntity = profileRepository.findByEmail(email);
        profileEntity.setName(profileDTO.getName());
        profileEntity.setSurname(profileDTO.getSurname());
        profileEntity.setEmail(profileDTO.getEmail());
        profileEntity.setPassword(profileDTO.getPassword());
        profileEntity.setRole(ProfileRole.ROLE_USER);
        profileEntity.setCreated_date(LocalDateTime.now());

        profileRepository.save(profileEntity);

        profileDTO.setCreated_date(profileEntity.getCreated_date());
        return profileEntity;
    }

    public List<ProfileDTO> getAll(){
        List<ProfileEntity> profileEntities = profileRepository.findAll();
        List<ProfileDTO> profileDTOS = new LinkedList<>();
        ProfileDTO dto = new ProfileDTO();
        for (ProfileEntity profileEntity : profileEntities) {
            dto.setId(profileEntity.getId());
            dto.setName(profileEntity.getName());
            dto.setSurname(profileEntity.getSurname());
            dto.setEmail(profileEntity.getEmail());
            dto.setPassword(profileEntity.getPassword());
            dto.setVisible(profileEntity.getVisible());
            dto.setStatus(profileEntity.getStatus());
            dto.setRole(profileEntity.getRole());
            dto.setPhoto_id(profileEntity.getPhoto_id());
            dto.setCreated_date(profileEntity.getCreated_date());

            profileDTOS.add(dto);
        }

        return profileDTOS;
    }
}
