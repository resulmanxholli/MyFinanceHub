package com.example.myfinancehub.repositories;

import com.example.myfinancehub.entities.Transaction;
import com.example.myfinancehub.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByType(CategoryType type);
}
