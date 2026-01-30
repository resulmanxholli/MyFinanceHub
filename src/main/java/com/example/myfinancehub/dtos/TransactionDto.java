package com.example.myfinancehub.dtos;

import com.example.myfinancehub.enums.PaymentMethod;
import com.example.myfinancehub.enums.CategoryType;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {

    @PositiveOrZero
    private Long id;

    @PositiveOrZero
    @Min(value = 0)
    @Max(value = 1000000)
    private double amount;

    @NotNull
    private CategoryType type;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private LocalDateTime date = LocalDateTime.now();

    @Size(max = 255)
    private String note;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Long categoryId;

    private CategoryDto category;
}
