
package org.example.expanse_tracking.Dao;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Request.SignUpRequest;
import org.example.expanse_tracking.Dto.Response.SignUpResponse;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Mapper.UserMapper;
import org.example.expanse_tracking.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public SignUpResponse register(SignUpRequest request) {

        // Check whether the email is already registered
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email is already registered"
            );
        }

        // Convert request to User entity and encode password
        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        User user = userMapper.toEntity(request, encodedPassword);

        // Save user to PostgreSQL
        User savedUser = userRepository.save(user);

        // Convert saved entity to response DTO
        return userMapper.toResponse(savedUser);
    }
}
