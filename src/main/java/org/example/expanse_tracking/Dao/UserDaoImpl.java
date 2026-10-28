
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

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.BadCredentialsException;

@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    // Register a new user
    @Override
    public SignUpResponse register(SignUpRequest request) {

        // Check whether email is already registered
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException(
                    "Email is already registered"
            );
        }

        // Convert request to entity and encode password
        User user = userMapper.toEntity(
                request,
                passwordEncoder.encode(request.getPassword())
        );

        // Save user to database
        User savedUser = userRepository.save(user);

        // Return response without exposing password
        return userMapper.toResponse(savedUser);
    }

    // Login an existing user
    @Override
    public LoginResponse login(LoginRequest request) {

        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BadCredentialsException(
                                "Invalid email or password"
                        )
                );

        // Verify password
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new BadCredentialsException(
                    "Invalid email or password"
            );
        }

        // Return user information
        return userMapper.toLoginResponse(user);
    }
}
