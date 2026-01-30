package com.example.myfinancehub.repositories;

import com.example.myfinancehub.entities.Category;
import com.example.myfinancehub.entities.Transaction;
import com.example.myfinancehub.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByType(CategoryType type);
}
