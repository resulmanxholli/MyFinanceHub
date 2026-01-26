package com.example.myfinancehub.dtos;

import com.example.myfinancehub.enums.PaymentMethod;
import com.example.myfinancehub.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {

    private Long id;

    private BigDecimal amount;

    private TransactionType type;

    private LocalDate date;

    private String note;

    private PaymentMethod paymentMethod;
}
