/**
 * Created By Arun Singh
 * Date:03-02-2025
 * Time:20:21
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.serviceImpl;

import com.nontech.enotes.entity.AccountStatus;
import com.nontech.enotes.entity.User;
import com.nontech.enotes.exception.ResourceNotFoundException;
import com.nontech.enotes.exception.SuccessException;
import com.nontech.enotes.repository.UserRepo;
import com.nontech.enotes.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public Boolean verifyAccount(Integer userId, String verificationCode) throws ResourceNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid user"));
        if(user.getStatus().getVerificationCode()==null)
        {
            throw new SuccessException("Account already verified");
        }
        if (user.getStatus().getVerificationCode().equals(verificationCode)) {
            AccountStatus status = user.getStatus();
            status.setIsActive(true);
            status.setVerificationCode(null);
            userRepo.save(user);
            return true;
        }
        return false;
    }
}
