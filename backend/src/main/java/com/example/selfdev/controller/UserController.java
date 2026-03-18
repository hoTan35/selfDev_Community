package com.example.selfdev.controller;

import com.example.selfdev.dto.UserRequestDto;
import com.example.selfdev.dto.UserResponseDto;
import com.example.selfdev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.login(requestDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }
}
