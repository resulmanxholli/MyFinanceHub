package com.example.myfinancehub.services;

import com.example.myfinancehub.dtos.TransactionDto;
import com.example.myfinancehub.enums.CategoryType;
import com.example.myfinancehub.infrastructure.services.CrudService;

import java.util.List;

public interface TransactionService extends CrudService<TransactionDto, Long> {
    List<TransactionDto> findByType(CategoryType type);
}
