package com.example.myfinancehub.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    private String username;

    private String email;

    private String password; // plain password input; service should hash
}
