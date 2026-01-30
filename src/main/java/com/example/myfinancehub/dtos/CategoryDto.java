package com.example.myfinancehub.dtos;

import com.example.myfinancehub.enums.CategoryType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {

    @PositiveOrZero
    private Long id;

    @Size(max = 50)
    private String name;

    @NotNull
    private CategoryType type;

    private boolean isDefault;
}
