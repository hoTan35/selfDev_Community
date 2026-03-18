package com.example.selfdev.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {
    private String title;
    private String content;
}
