package org.example.expanse_tracking.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import org.example.expanse_tracking.Enums.Role;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private Role role;
}
