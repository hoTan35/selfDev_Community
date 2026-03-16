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
    private Long authorId; // 로그인 구현 전까지는 ID를 직접 받습니다.
}
