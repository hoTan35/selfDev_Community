package com.example.selfdev.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
