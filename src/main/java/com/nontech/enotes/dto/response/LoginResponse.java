/**
 * Created By Arun Singh
 * Date:04-02-2025
 * Time:10:16
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.dto.response;

import com.nontech.enotes.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private UserDto userDto;
    private String token;
}
