package org.example.expanse_tracking.Mapper;

import org.example.expanse_tracking.Dto.Request.SignUpRequest;
import org.example.expanse_tracking.Dto.Response.SignUpResponse;
import org.example.expanse_tracking.Dto.Response.LoginResponse;
import org.example.expanse_tracking.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(SignUpRequest request);

    SignUpResponse toResponse(User user);

    @Mapping(target = "token", ignore = true)
    LoginResponse toLoginResponse(User user);
}