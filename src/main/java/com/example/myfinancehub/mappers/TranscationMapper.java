package com.example.myfinancehub.mappers;

import com.example.myfinancehub.dtos.TransactionDto;
import com.example.myfinancehub.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TranscationMapper extends BaseMapper<TransactionDto, Transaction> {
}
