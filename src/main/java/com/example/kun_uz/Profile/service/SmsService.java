package com.example.kun_uz.Profile.service;

import com.example.kun_uz.ExceptionHandler.AppBadException;
import com.example.kun_uz.Profile.dto.SmsAuthResponseDTO;
import com.example.kun_uz.Profile.entity.SmsTokenEntity;
import com.example.kun_uz.Profile.repository.SmsHistoryRepository;
import com.example.kun_uz.Profile.repository.SmsTokenRepository;
import com.example.kun_uz.util.RandomUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SmsService {
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    @Autowired
    private SmsTokenRepository smsTokenRepository;

    public Integer sendRegistrationSms(String phoneNumber) {
        int code = RandomUtil.getRandomInt();
        String message = "This is test from Eskiz";
        sendSms(phoneNumber, message);
        return code;
    }


    private void sendSms(String phone, String message) {
        try {
            Long smsCount = smsHistoryRepository.getSmsCount(phone, LocalDateTime.now().minusMinutes(1), LocalDateTime.now());
            if (smsCount >= 3) {
                throw new AppBadException("Limit reached");
            }

            RequestBody formBody = new FormBody.Builder()
                    .add("mobile_phone", phone)
                    .add("message", message)
                    .add("from", "4546")
                    .build();

            Request request = new Request.Builder()
                    .url("https://notify.eskiz.uz/api/message/sms/send")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + getToken())
                    .post(formBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Call call = client.newCall(request);

            Response response = call.execute();
            System.out.println(response.body().string());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String getToken() {
        LocalDate localDate = LocalDate.now();
        Optional<SmsTokenEntity> byId = smsTokenRepository.findById(1);
        if (byId.isPresent()) {
            SmsTokenEntity smsTokenEntity = byId.get();
            if (smsTokenEntity.getCreateAt().plusDays(29).isBefore(localDate)) {
                String newToken = getNewToken();
                smsTokenEntity.setToken(newToken);
                smsTokenEntity.setCreateAt(localDate);
                smsTokenRepository.save(smsTokenEntity);
                return newToken;
            } else {
                return smsTokenEntity.getToken();
            }
        } else {
            return getNewToken();
        }
    }

    private String getNewToken() {
        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("email", "ulugbeksamadov007@gmail.com")
                    .add("password", "tSw3GACLVG8xbxasfghQ7XyFIrWMJwYL35ilWkG6")
                    .add("from", "4546")
                    .build();

            Request request = new Request.Builder()
                    .url("https://notify.eskiz.uz/api/auth/login")
                    .addHeader("Content-Type", "application/json")
                    .post(formBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Call call = client.newCall(request);

            Response response = call.execute();
            String json = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            SmsAuthResponseDTO result = mapper.readValue(json, SmsAuthResponseDTO.class);
            SmsTokenEntity smsTokenEntity = new SmsTokenEntity();
            smsTokenEntity.setToken(result.getData().getToken());
            smsTokenEntity.setCreateAt(LocalDate.now());
            smsTokenRepository.save(smsTokenEntity);
            return result.getData().getToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

}
