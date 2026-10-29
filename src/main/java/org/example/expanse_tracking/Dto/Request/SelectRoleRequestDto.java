
package org.example.expanse_tracking.Dto.Request;

import jakarta.validation.constraints.NotNull;
import org.example.expanse_tracking.Enums.Role;

public record SelectRoleRequestDto(
        @NotNull(message = "Role is required")
        Role role
) {}
