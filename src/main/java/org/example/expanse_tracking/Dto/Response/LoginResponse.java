package org.example.expanse_tracking.Dto.Response;

import org.example.expanse_tracking.Enums.Role;

public record LoginResponse(
        Long id,
        String fullName,
        String email,
        Role role,
        String token
) {}