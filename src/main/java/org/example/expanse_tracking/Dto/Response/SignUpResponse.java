package org.example.expanse_tracking.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import org.example.expanse_tracking.Enums.Role;

import java.time.LocalDateTime;

@Getter
@Builder
public class SignUpResponse {

    private Long id;
    private String fullName;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}
