package com.example.selfdev.service;

import com.example.selfdev.dto.PostRequestDto;
import com.example.selfdev.dto.PostResponseDto;
import com.example.selfdev.entity.Post;
import com.example.selfdev.entity.User;
import com.example.selfdev.repository.PostRepository;
import com.example.selfdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {
        User author = userRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + requestDto.getAuthorId()));

        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .author(author)
                .build();

        Post savedPost = postRepository.save(post);
        return convertToResponseDto(savedPost);
    }

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id));
        return convertToResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id));
        
        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());
        
        return convertToResponseDto(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id));
        postRepository.delete(post);
    }

    private PostResponseDto convertToResponseDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorName(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
