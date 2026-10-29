
package org.example.expanse_tracking.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.expanse_tracking.Enums.Role;

@Getter
@Setter
@Builder
public class LoginResponse {

    private Long id;
    private String fullName;
    private String email;
    private Role role;
    private String token;
}
