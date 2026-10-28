package org.example.expanse_tracking.Dao;

import org.example.expanse_tracking.Dto.Request.SignUpRequest;
import org.example.expanse_tracking.Dto.Response.SignUpResponse;

public interface UserDao {

    SignUpResponse register(SignUpRequest request);
}