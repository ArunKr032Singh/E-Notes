package com.nontech.enotes.service;

import com.nontech.enotes.exception.ResourceNotFoundException;

public interface HomeService {
    public Boolean verifyAccount(Integer userId, String verificationCode) throws ResourceNotFoundException;
}
