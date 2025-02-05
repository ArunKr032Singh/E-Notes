/**
 * Created By Arun Singh
 * Date:04-02-2025
 * Time:09:05
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.config.security;

import com.nontech.enotes.entity.User;
import com.nontech.enotes.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email");
        }
        return new CustomUserDetails(user);
    }
}
