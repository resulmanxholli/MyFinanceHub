package com.example.myfinancehub.services.impls;

import com.example.myfinancehub.dtos.CategoryDto;
import com.example.myfinancehub.exceptions.categories.CategoryConflictException;
import com.example.myfinancehub.exceptions.categories.CategoryNotFoundException;
import com.example.myfinancehub.mappers.CategoryMapper;
import com.example.myfinancehub.repositories.CategoryRepository;
import com.example.myfinancehub.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryDto add(CategoryDto dto) {
        if(dto.getId() != null && dto.getId() != 0){
            if (repository.existsById(dto.getId())) {
                throw new CategoryConflictException("Category already exists");
            }
        }
        var entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<CategoryDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public CategoryDto findOne(Long id) {
       var category = repository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
       return mapper.toDto(category);
    }

    @Override
    public CategoryDto modify(Long id, CategoryDto dto) {
        if (!repository.existsById(id)) {
            throw new CategoryNotFoundException("Category not found");
        }
        var entity = mapper.toEntity(dto);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void remove(Long id) {
        if (!repository.existsById(id)) {
            throw new CategoryNotFoundException("Category not found");
        }
        repository.deleteById(id);
    }
}
