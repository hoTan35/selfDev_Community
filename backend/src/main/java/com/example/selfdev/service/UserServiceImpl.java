package com.example.selfdev.service;

import com.example.selfdev.dto.UserRequestDto;
import com.example.selfdev.dto.UserResponseDto;
import com.example.selfdev.entity.User;
import com.example.selfdev.repository.UserRepository;
import com.example.selfdev.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public UserResponseDto signup(UserRequestDto requestDto) {
        // 아이디 중복 확인
        if (userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }

        // 비밀번호 암호화
        User user = User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .email(requestDto.getEmail())
                .role(User.Role.USER) // 기본 역할은 USER
                .build();

        User savedUser = userRepository.save(user);
        return convertToResponseDto(savedUser, null);
    }

    @Override
    public UserResponseDto login(UserRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 비밀번호 암호화 검증
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 JWT 토큰 생성
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole().name());

        return convertToResponseDto(user, token);
    }

    @Override
    public UserResponseDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        
        return convertToResponseDto(user, null);
    }

    private UserResponseDto convertToResponseDto(User user, String token) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .build();
    }
}
