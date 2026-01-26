package com.example.myfinancehub.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BudgetDto {

    private Long id;

    private Integer month;

    private Integer year;

    private double limitAmount;
}
