package com.example.myfinancehub.dtos;

import com.example.myfinancehub.enums.AccountType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;

    private String name;

    private AccountType type;

    private double initialBalance;
}
