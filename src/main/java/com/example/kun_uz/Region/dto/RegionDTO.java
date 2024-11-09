package com.example.kun_uz.Region.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class RegionDTO {
   private Integer id;
   private Integer order_number;
   private String name_uz;
   private String name_ru;
   private String name_en;
   private Boolean visible;
   private LocalDateTime created_date;

}
