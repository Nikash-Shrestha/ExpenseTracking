
package org.example.expanse_tracking.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.expanse_tracking.Dao.UserDao;
import org.example.expanse_tracking.Dto.Request.SignUpRequest;
import org.example.expanse_tracking.Dto.Request.LoginRequest;
import org.example.expanse_tracking.Dto.Request.SelectRoleRequestDto;
import org.example.expanse_tracking.Dto.Response.SignUpResponse;
import org.example.expanse_tracking.Dto.Response.LoginResponse;
import org.example.expanse_tracking.Dto.Response.SelectRoleResponseDto;
import org.example.expanse_tracking.Dto.Response.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDao userDao;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponse register(
            @Valid @RequestBody SignUpRequest request) {
        return userDao.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {
        return userDao.login(request);
    }


}
