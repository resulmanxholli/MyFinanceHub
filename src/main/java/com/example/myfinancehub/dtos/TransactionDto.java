package com.example.myfinancehub.dtos;

import com.example.myfinancehub.enums.PaymentMethod;
import com.example.myfinancehub.enums.CategoryType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {

    private Long id;

    private double amount;

    private CategoryType type;

    private LocalDate date;

    private String note;

    private PaymentMethod paymentMethod;

    private Long categoryId;

    private CategoryDto categoryName;
}
