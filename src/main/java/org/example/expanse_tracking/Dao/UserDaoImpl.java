package org.example.expanse_tracking.Dao;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Request.SignUpRequest;
import org.example.expanse_tracking.Dto.Request.LoginRequest;
import org.example.expanse_tracking.Dto.Response.SignUpResponse;
import org.example.expanse_tracking.Dto.Response.LoginResponse;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Exception.AlreadyExistsException;
import org.example.expanse_tracking.Mapper.UserMapper;
import org.example.expanse_tracking.Repository.UserRepository;
import org.example.expanse_tracking.Util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    // Register a new user
    @Override
    public SignUpResponse register(SignUpRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new AlreadyExistsException(
                    "Email is already registered"
            );
        }

        // Map request to entity
        User user = userMapper.toEntity(request);

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    // Login an existing user and generate JWT
    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new BadCredentialsException(
                                "Invalid email or password"
                        )
                );

        // Verify password
        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword()
        )) {
            throw new BadCredentialsException(
                    "Invalid email or password"
            );
        }

        // Generate JWT containing the user's role
        String token = jwtUtil.generateToken(
                user.getEmail(),
                Set.of(user.getRole().name())
        );

        // Return immutable LoginResponse with token
        return new LoginResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                token
        );
    }
}