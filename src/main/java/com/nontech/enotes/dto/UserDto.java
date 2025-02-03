/**
 * Created By Arun Singh
 * Date:03-02-2025
 * Time:10:02
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobNo;

    private List<RoleDto> roles;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoleDto{
        private Integer id;
        private String name;
    }
}
