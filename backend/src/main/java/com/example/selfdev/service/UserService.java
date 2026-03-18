package com.example.selfdev.service;

import com.example.selfdev.dto.UserRequestDto;
import com.example.selfdev.dto.UserResponseDto;

public interface UserService {
    /**
     * 회원 가입
     */
    UserResponseDto signup(UserRequestDto requestDto);

    /**
     * 로그인
     */
    UserResponseDto login(UserRequestDto requestDto);

    /**
     * 사용자 정보 조회
     */
    UserResponseDto getUserInfo(Long userId);
}
