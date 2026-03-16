package com.example.selfdev.service;

import com.example.selfdev.dto.UserRequestDto;
import com.example.selfdev.dto.UserResponseDto;
import com.example.selfdev.entity.User;
import com.example.selfdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponseDto signup(UserRequestDto requestDto) {
        // 아이디 중복 확인
        if (userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }

        // 비밀번호 암호화는 나중에 Security 적용 시 추가 예정
        User user = User.builder()
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .email(requestDto.getEmail())
                .role(User.Role.USER) // 기본 역할은 USER
                .build();

        User savedUser = userRepository.save(user);
        return convertToResponseDto(savedUser);
    }

    @Override
    public UserResponseDto login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 비밀번호 단순 비교 (임시)
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return convertToResponseDto(user);
    }

    @Override
    public UserResponseDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        
        return convertToResponseDto(user);
    }

    private UserResponseDto convertToResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
