package com.example.myfinancehub.dtos;

import com.example.myfinancehub.enums.CategoryType;
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

    private CategoryType type;

    private boolean isDefault;
}
