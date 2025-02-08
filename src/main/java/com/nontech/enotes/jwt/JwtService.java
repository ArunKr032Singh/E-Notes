/**
 * Created By Arun Singh
 * Date:05-02-2025
 * Time:08:50
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.jwt;

import com.nontech.enotes.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public String generateToken(User user);
    public String extractUsername(String token);
    public Boolean validateToken(String token, UserDetails userDetails);

}
