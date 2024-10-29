package com.example.kun_uz.service;

import com.example.kun_uz.dto.RegionDTO;
import com.example.kun_uz.entity.RegionEntity;
import com.example.kun_uz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO create(RegionDTO regionDTO) {
        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setOrder_number(regionDTO.getOrder_number());
        regionEntity.setName_uz(regionDTO.getName_uz());
        regionEntity.setName_en(regionDTO.getName_en());
        regionEntity.setName_ru(regionDTO.getName_ru());
        regionEntity.setVisible(Boolean.TRUE);
        regionEntity.setCreated_date(LocalDateTime.now());

        regionRepository.save(regionEntity);
        regionDTO.setId(regionEntity.getId());
        return regionDTO;
    }

    public RegionDTO update(Integer id, RegionDTO regionDTO) {
        RegionEntity regionEntity = regionRepository.findById(id).get();
        regionEntity.setOrder_number(regionDTO.getOrder_number());
        regionEntity.setName_uz(regionDTO.getName_uz());
        regionEntity.setName_en(regionDTO.getName_en());
        regionEntity.setName_ru(regionDTO.getName_ru());
        regionRepository.save(regionEntity);
        regionDTO.setId(regionEntity.getId());

        return regionDTO;
    }

    public void delete(Integer id) {
        RegionEntity regionEntity = regionRepository.findById(id).get();
        regionEntity.setVisible(Boolean.FALSE);
        regionRepository.save(regionEntity);
    }

    public List<RegionDTO> getAll() {
        List<RegionEntity> regionEntityList = regionRepository.findAll();
        List<RegionDTO> regionDTOList = new LinkedList<>();
        for (RegionEntity regionEntity : regionEntityList) {
            RegionDTO regionDTO = new RegionDTO();
            regionDTO.setId(regionEntity.getId());
            regionDTO.setOrder_number(regionEntity.getOrder_number());
            regionDTO.setName_uz(regionEntity.getName_uz());
            regionDTO.setName_en(regionEntity.getName_en());
            regionDTO.setName_ru(regionEntity.getName_ru());
            regionDTO.setVisible(regionEntity.getVisible());
            regionDTO.setCreated_date(regionEntity.getCreated_date());
            regionDTOList.add(regionDTO);
        }
        return regionDTOList;
    }


}
