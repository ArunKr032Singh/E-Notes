package com.nontech.enotes.service;

import com.nontech.enotes.dto.UserDto;

public interface UserService {
    public Boolean register(UserDto userDto, String url) throws Exception;
}
