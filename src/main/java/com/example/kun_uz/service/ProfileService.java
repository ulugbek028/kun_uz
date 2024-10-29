package com.example.kun_uz.service;

import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.entity.ProfileEntity;
import com.example.kun_uz.Enum.ProfileRole;
import com.example.kun_uz.Enum.ProfileStatus;
import com.example.kun_uz.exp.AppBadRequestException;
import com.example.kun_uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO create(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName(profileDTO.getName());
        profileEntity.setSurname(profileDTO.getSurname());
        profileEntity.setEmail(profileDTO.getEmail());
        profileEntity.setPassword(profileDTO.getPassword());
        profileEntity.setVisible(Boolean.TRUE);
        profileEntity.setRole(ProfileRole.ROLE_USER);
        profileEntity.setCreatedDate(LocalDateTime.now());

        profileRepository.save(profileEntity);

        profileDTO.setId(profileEntity.getId());
        profileDTO.setCreatedDate(profileEntity.getCreatedDate());
        return profileDTO;
    }

    public ProfileEntity update(String email) {
        Optional<ProfileEntity> profileList = profileRepository.findByEmailAndVisibleTrue(email);
        if(profileList.isPresent()) {
            ProfileEntity profileEntity = profileList.get();

            profileEntity.setStatus(ProfileStatus.ACTIVE);
            profileEntity.setRole(ProfileRole.ROLE_USER);
            profileEntity.setPhotoId("photo_id");
            profileEntity.setVisible(Boolean.TRUE);
            profileRepository.save(profileEntity);

            return profileEntity;
        } else  {
            throw new AppBadRequestException("Email not found");

        }
    }

    public ProfileEntity userUpdate(String email, ProfileDTO profileDTO) {
        Optional<ProfileEntity> profileEntity = profileRepository.findByEmailAndVisibleTrue(email);

        if (profileEntity.isPresent()) {
            ProfileEntity profile = profileEntity.get();
            profile.setName(profileDTO.getName());
            profile.setSurname(profileDTO.getSurname());
            profile.setEmail(profileDTO.getEmail());
            profile.setPassword(profileDTO.getPassword());
            profile.setRole(ProfileRole.ROLE_USER);
            profile.setCreatedDate(LocalDateTime.now());

            profileRepository.save(profile);

            profileDTO.setCreatedDate(profile.getCreatedDate());
            return profile;
        }else {
            throw new AppBadRequestException("Email not found");
        }
    }


    public List<ProfileDTO> getAll(){
        Iterable<ProfileEntity> profileEntities = profileRepository.findAll();
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
            dto.setPhotoId(profileEntity.getPhotoId());
            dto.setCreatedDate(profileEntity.getCreatedDate());

            profileDTOS.add(dto);
        }

        return profileDTOS;
    }
}
