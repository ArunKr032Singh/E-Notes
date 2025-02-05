package com.nontech.enotes.service;

import com.nontech.enotes.dto.UserDto;
import com.nontech.enotes.dto.request.LoginRequest;
import com.nontech.enotes.dto.response.LoginResponse;

public interface UserService {
    public Boolean register(UserDto userDto, String url) throws Exception;

    LoginResponse loginUser(LoginRequest loginRequest);
}
