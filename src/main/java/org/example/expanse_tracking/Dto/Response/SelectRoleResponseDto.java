package org.example.expanse_tracking.Dto.Response;

import org.example.expanse_tracking.Enums.Role;

public record SelectRoleResponseDto(
        Long userId,
        Role role,
        String message
) {}