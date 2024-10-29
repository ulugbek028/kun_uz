package com.example.kun_uz.service;

import com.example.kun_uz.dto.CategoryDTO;
import com.example.kun_uz.entity.CategoryEntity;
import com.example.kun_uz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryDTO categoryDTO) {
        CategoryEntity dto = new CategoryEntity();
        dto.setOrder_number(categoryDTO.getOrder_number());
        dto.setName_uz(categoryDTO.getName_uz());
        dto.setName_en(categoryDTO.getName_en());
        dto.setName_ru(categoryDTO.getName_ru());
        dto.setVisible(Boolean.TRUE);
        dto.setCreated_date(LocalDateTime.now());

        categoryRepository.save(dto);

        categoryDTO.setId(dto.getId());
        return categoryDTO;
    }

    public CategoryDTO update(Integer id, CategoryDTO dto) {

        CategoryEntity dtoEntity = categoryRepository.findById(id).get();
        dtoEntity.setOrder_number(dto.getOrder_number());
        dtoEntity.setName_uz(dto.getName_uz());
        dtoEntity.setName_en(dto.getName_en());
        dtoEntity.setName_ru(dto.getName_ru());
        dtoEntity.setCreated_date(LocalDateTime.now());

        categoryRepository.save(dtoEntity);

        dto.setId(dtoEntity.getId());
        return dto;
    }

    public void delete(Integer id) {
        CategoryEntity dto = categoryRepository.getById(id);
        dto.setVisible(false);
        categoryRepository.save(dto);
    }

    public List<CategoryDTO> getAll() {
        List<CategoryEntity> categoryList = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity entity : categoryList) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setOrder_number(entity.getOrder_number());
            dto.setName_uz(entity.getName_uz());
            dto.setName_en(entity.getName_en());
            dto.setName_ru(entity.getName_ru());
            dto.setCreated_date(entity.getCreated_date());
            dtoList.add(dto);
        }
        return dtoList;
    }


}
