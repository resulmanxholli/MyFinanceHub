package com.example.myfinancehub.dtos;

import com.example.myfinancehub.enums.TransactionType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {

    private Long id;

    private String name;

    private TransactionType type;

    private boolean isDefault;
}
