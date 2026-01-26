package com.example.myfinancehub.services.impls;

import com.example.myfinancehub.dtos.TransactionDto;
import com.example.myfinancehub.entities.Transaction;
import com.example.myfinancehub.exceptions.categories.CategoryConflictException;
import com.example.myfinancehub.exceptions.categories.CategoryNotFoundException;
import com.example.myfinancehub.exceptions.transactions.TransactionConflictException;
import com.example.myfinancehub.exceptions.transactions.TransactionNotFoundException;
import com.example.myfinancehub.mappers.TranscationMapper;
import com.example.myfinancehub.repositories.TranscationRepository;
import com.example.myfinancehub.services.TranscationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@
        RequiredArgsConstructor
public class TranscationServiceImpl implements TranscationService {
    private final TranscationRepository repository;
    private final TranscationMapper mapper;

    @Override
    public TransactionDto add(TransactionDto dto) {
        if (dto.getId() != null && dto.getId() != 0) {
            if (repository.existsById(dto.getId())) {
                throw new TransactionConflictException("Transaction already exists");
            }
        }
        var entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<TransactionDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public TransactionDto findOne(Long id) {
        var transaction = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
        return mapper.toDto(transaction);
    }

    @Override
    public TransactionDto modify(Long id, TransactionDto dto) {
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
