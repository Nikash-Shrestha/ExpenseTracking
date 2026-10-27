package org.example.expanse_tracking.Dao;

import org.example.expanse_tracking.Dto.Request.RegisterRequest;
import org.example.expanse_tracking.Dto.Response.UserResponse;

public interface UserDao {
    UserResponse register(RegisterRequest request);
}
