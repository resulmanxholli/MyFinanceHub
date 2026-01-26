package com.example.myfinancehub.services;

import com.example.myfinancehub.dtos.TransactionDto;
import com.example.myfinancehub.infrastructure.services.CrudService;

public interface TranscationService extends CrudService<TransactionDto, Long> {
}
