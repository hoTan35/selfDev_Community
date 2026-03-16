package com.example.selfdev.dto;

import com.example.selfdev.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private User.Role role;
}
