package com.example.myfinancehub.mappers;

import com.example.myfinancehub.dtos.TransactionDto;
import com.example.myfinancehub.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends BaseMapper<TransactionDto, Transaction> {

    @Mapping(source = "category.id", target = "categoryId")
    TransactionDto toDto(Transaction transaction);

    @Mapping(source = "categoryId", target = "category.id")
    Transaction toEntity(TransactionDto dto);

    List<TransactionDto> toDtoList(List<Transaction> transactions);
}
