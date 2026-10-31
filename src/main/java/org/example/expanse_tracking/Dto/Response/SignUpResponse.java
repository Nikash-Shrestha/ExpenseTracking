package org.example.expanse_tracking.Dto.Response;

import org.example.expanse_tracking.Enums.Role;
import java.time.LocalDateTime;

public record SignUpResponse(
        Long id,
        String fullName,
        String email,
        Role role,
        LocalDateTime createdAt
) {}