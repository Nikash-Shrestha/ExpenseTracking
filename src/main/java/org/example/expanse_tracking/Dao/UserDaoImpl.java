package org.example.expanse_tracking.Dao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Request.RegisterRequest;
import org.example.expanse_tracking.Dto.Response.UserResponse;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Enums.Role;
import org.example.expanse_tracking.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email is already registered"
            );
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }
}
