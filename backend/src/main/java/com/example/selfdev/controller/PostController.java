package com.example.selfdev.controller;

import com.example.selfdev.dto.PostRequestDto;
import com.example.selfdev.dto.PostResponseDto;
import com.example.selfdev.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto) {
        return ResponseEntity.ok(postService.createPost(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return ResponseEntity.ok(postService.updatePost(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
