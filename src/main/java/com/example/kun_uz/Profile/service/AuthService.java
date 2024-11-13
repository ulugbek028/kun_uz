package com.example.kun_uz.Profile.service;

import com.example.kun_uz.Attach.service.AttachService;
import com.example.kun_uz.Enum.ProfileRole;
import com.example.kun_uz.Enum.ProfileStatus;
import com.example.kun_uz.ExceptionHandler.AppBadException;
import com.example.kun_uz.Profile.dto.AuthDTO;
import com.example.kun_uz.Profile.dto.ProfileDTO;
import com.example.kun_uz.Profile.dto.RegistrationDTO;
import com.example.kun_uz.Profile.dto.SmsConfirmDTO;
import com.example.kun_uz.config.CustomUserDetails;
import com.example.kun_uz.Profile.entity.ProfileEntity;
import com.example.kun_uz.Profile.entity.SmsHistoryEntity;
import com.example.kun_uz.Profile.repository.ProfileRepository;
import com.example.kun_uz.Profile.repository.SmsHistoryRepository;
import com.example.kun_uz.exp.AppBadRequestException;
import com.example.kun_uz.util.JwtUtil;
import com.example.kun_uz.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;
    @Autowired
    private SmsHistoryService smsHistoryService;

    @Autowired
    private AttachService attachService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String registration(RegistrationDTO dto) {
        // check email exists
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
        if (optional.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.md5(dto.getPassword()));
        entity.setSurname(dto.getSurname());
        entity.setStatus(ProfileStatus.IN_REGISTRATION);
        entity.setVisible(Boolean.TRUE);
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);
        //

//        StringBuilder sb = new StringBuilder();
//        sb.append("<h1 style=\"text-align: center\"> Complete Registration</h1>");
//        sb.append("<br>");
//        sb.append("<p>Click the link below to complete registration</p>\n");
//        sb.append("<p><a style=\"padding: 5px; background-color: indianred; color: white\"  href=\"http://localhost:8081/auth/registration/confirm/")
//                .append(entity.getId()).append("\" target=\"_blank\">Click Th</a></p>\n");
//
//        emailSendingService.sendMimeMessage(dto.getEmail(), "Complite Registration", sb.toString());

        Integer code = smsService.sendRegistrationSms(dto.getEmail());
        smsHistoryService.addHistoryCode(dto.getEmail(), String.valueOf(code), LocalDateTime.now());
        System.out.println(code);

        return "Email was sent";
    }

    public String registrationConfirm(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()) {
            return "Not Completed";
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(ProfileStatus.IN_REGISTRATION)) {
            return "Not Completed";
        }
        entity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(entity);
        return "Completed";
    }

    public String smsConfirm(SmsConfirmDTO dto, LocalDateTime dateTime) {
        boolean b = profileRepository.existsByUsername(dto.getPhone());
        if (b) {
            SmsHistoryEntity latestCodeByPhone = smsHistoryRepository.findLatestCodeByPhone(dto.getPhone());
            Duration duration = Duration.between(dateTime, latestCodeByPhone.getCreatedDate());
            if (duration.abs().getSeconds() >= 120) {
                throw new AppBadException("SMS timed out");
            }
            if (latestCodeByPhone.getCode().equals(dto.getCode())) {
                profileRepository.updateStatus(dto.getPhone());
                return "Active account";
            } else {
                throw new AppBadException("the code is incorrect");
            }
        } else {
            throw new AppBadException("The phone number could not be found or it has already been registered");
        }
    }


    /*public ProfileDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Email or Password wrong");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getPassword().equals(MD5Util.md5(dto.getPassword()))) {
            throw new AppBadRequestException("Email or Password wrong");
        }
        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadRequestException("User Not Active");
        }
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(entity.getName());
        profileDTO.setSurname(entity.getSurname());
        profileDTO.setEmail(entity.getEmail());
        profileDTO.setRole(entity.getRole());
        profileDTO.setJwtToken(JwtUtil.encode(entity.getEmail(), entity.getRole().toString()));
        return profileDTO;
    }*/


    public ProfileDTO login(AuthDTO dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
            if (authentication.isAuthenticated()) {
                CustomUserDetails profile = (CustomUserDetails) authentication.getPrincipal();

                ProfileDTO profileDTO = new ProfileDTO();
                profileDTO.setId(profile.getId());
                profileDTO.setName(profile.getName());
                profileDTO.setSurname(profile.getSurname());
                profileDTO.setEmail(profile.getEmail());
                profileDTO.setRole(profile.getRole());
                profileDTO.setPassword(profile.getPassword());
                profileDTO.setStatus(profile.getStatus());
                profileDTO.setJwtToken(JwtUtil.encode(profile.getEmail(), profile.getRole().toString()));
//                profileDTO.setPhotoId(attachService.getDTO());




//                AttachDTO photo = new AttachDTO();
////                photo.setId(profile.get);
//                photo.setUrl(attachService.getUrl(profile.getEmail()));
//                profileDTO.setPhoto(photo);
                return profileDTO;
            }
        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException("Phone or password wrong");
        }
        throw new UsernameNotFoundException("Phone or password wrong");
    }

}
