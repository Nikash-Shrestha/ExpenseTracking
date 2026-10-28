
package org.example.expanse_tracking.Mapper;

import org.example.expanse_tracking.Dto.Request.SignUpRequest;
import org.example.expanse_tracking.Dto.Response.SignUpResponse;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(SignUpRequest request, String encodedPassword) {
        return User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(Role.USER)
                .build();
    }

    public SignUpResponse toResponse(User user) {
        return SignUpResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
