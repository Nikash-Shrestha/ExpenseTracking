package org.example.expanse_tracking.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dao.UserDao;
import org.example.expanse_tracking.Dto.Request.RegisterRequest;
import org.example.expanse_tracking.Dto.Response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDao userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(
            @Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }
}
