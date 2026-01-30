package com.example.myfinancehub.config;

import com.example.myfinancehub.entities.Category;
import com.example.myfinancehub.enums.CategoryType;
import com.example.myfinancehub.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitialization implements CommandLineRunner {
    private final CategoryRepository repository;

    @Override
    public void run(String... args) throws Exception {

        if(repository.count() == 0){
            Category rent = Category.builder()
                    .name("Rent")
                    .type(CategoryType.EXPENSE)
                    .isDefault(true)
                    .build();
            repository.save(rent);

            Category pets = Category.builder()
                    .name("Pets")
                    .type(CategoryType.EXPENSE)
                    .isDefault(true)
                    .build();
            repository.save(pets);

            Category transport = Category.builder()
                    .name("Transport")
                    .type(CategoryType.EXPENSE)
                    .isDefault(true)
                    .build();
            repository.save(transport);

            Category gift = Category.builder()
                    .name("Gift")
                    .type(CategoryType.EXPENSE)
                    .isDefault(true)
                    .build();
            repository.save(gift);

            Category education = Category.builder()
                    .name("Education")
                    .type(CategoryType.EXPENSE)
                    .isDefault(true)
                    .build();

            repository.save(education);

            Category household = Category.builder()
                    .name("Household")
                    .type(CategoryType.EXPENSE)
                    .isDefault(true)
                    .build();
            repository.save(household);


            Category salary = Category.builder()
                    .name("Salary")
                    .type(CategoryType.INCOME)
                    .isDefault(true)
                    .build();
            repository.save(salary);

            Category pettyCash = Category.builder()
                    .name("Petty Cash")
                    .type(CategoryType.INCOME)
                    .isDefault(true)
                    .build();
            repository.save(pettyCash);

            Category bonus = Category.builder()
                    .name("Bonus")
                    .type(CategoryType.INCOME)
                    .isDefault(true)
                    .build();
            repository.save(bonus);

            Category allowance = Category.builder()
                    .name("Allowance")
                    .type(CategoryType.INCOME)
                    .isDefault(true)
                    .build();
            repository.save(allowance);


        }
    }

 }
