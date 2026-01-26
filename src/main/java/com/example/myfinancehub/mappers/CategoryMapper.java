package com.example.myfinancehub.mappers;

import com.example.myfinancehub.dtos.CategoryDto;
import com.example.myfinancehub.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryDto, Category> {
}
