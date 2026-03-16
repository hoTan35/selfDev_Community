package com.example.selfdev.dto;

import com.example.selfdev.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String username;
    private String password;
    private String email;
}
