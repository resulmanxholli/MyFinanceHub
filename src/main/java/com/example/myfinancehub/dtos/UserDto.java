package com.example.myfinancehub.dtos;

import com.example.myfinancehub.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private Role role;

    private LocalDateTime createdAt;
}
