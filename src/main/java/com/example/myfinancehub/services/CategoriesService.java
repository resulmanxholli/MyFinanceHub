package com.example.myfinancehub.services;

import com.example.myfinancehub.dtos.CategoryDto;
import com.example.myfinancehub.enums.CategoryType;
import com.example.myfinancehub.infrastructure.services.CrudService;

import java.util.List;

public interface CategoriesService extends CrudService<CategoryDto, Long> {
    List<CategoryDto> findByType(CategoryType type);
}
