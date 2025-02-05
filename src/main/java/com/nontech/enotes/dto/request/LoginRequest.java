/**
 * Created By Arun Singh
 * Date:04-02-2025
 * Time:10:14
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
