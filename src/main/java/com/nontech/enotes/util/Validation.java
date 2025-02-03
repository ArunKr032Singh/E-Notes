/**
 * Created By Arun Singh
 * Date:29-01-2025
 * Time:10:17
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.util;

import com.nontech.enotes.dto.CategoryDto;
import com.nontech.enotes.dto.UserDto;
import com.nontech.enotes.exception.ExistsDataException;
import com.nontech.enotes.exception.ValidationException;
import com.nontech.enotes.repository.RoleRepo;
import com.nontech.enotes.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Validation {
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    public void categoryValidation(CategoryDto categoryDto) {
        Map<String, String> error = new HashMap<>();
        if (ObjectUtils.isEmpty(categoryDto)) {
            throw new IllegalArgumentException("category Object/JSON shouldn't be null or empty");
        } else {
            //validation name field
            if (ObjectUtils.isEmpty(categoryDto.getName())) {
                error.put("name", "name field is empty or null");
            } else {
                if (categoryDto.getName().length() < 10) {
                    error.put("name", "name length min 10");
                }
                if (categoryDto.getName().length() > 100) {
                    error.put("name", "name length max 100");
                }
            }

            //validation description field
            if (ObjectUtils.isEmpty(categoryDto.getDescription())) {
                error.put("description", "description field is empty or null");
            }

            //validation isActive field
            if (ObjectUtils.isEmpty(categoryDto.getIsActive())) {
                error.put("isActive", "isActive field is empty or null");
            } else {
                if (categoryDto.getIsActive() != Boolean.TRUE.booleanValue() && categoryDto.getIsActive() != Boolean.FALSE.booleanValue()) {
                    error.put("isActive", "isActive is invalid");
                }

            }

        }

        if (!error.isEmpty()) {
            throw new ValidationException(error);
        }
    }

    public void userValidation(UserDto userDto) {
        if (!StringUtils.hasText(userDto.getFirstName())) {
            throw new IllegalArgumentException("First name is invalid");
        }

        if (!StringUtils.hasText(userDto.getLastName())) {
            throw new IllegalArgumentException("Last name is invalid");
        }

        if (!StringUtils.hasText(userDto.getEmail()) || !userDto.getEmail().matches(Constatnts.EMAIL_REGEX)) {
            throw new IllegalArgumentException("Email is invalid");
        }else{
            // valid emal exist
            Boolean existEmail = userRepo.existsByEmail(userDto.getEmail());
            if(existEmail)
            {
                throw new ExistsDataException("Email already exist");
            }
        }

        if (!StringUtils.hasText(userDto.getMobNo()) || !userDto.getMobNo().matches(Constatnts.MOBNO_REGEX)) {
            throw new IllegalArgumentException("Mob No is invalid");
        }

        if (CollectionUtils.isEmpty(userDto.getRoles())) {
            throw new IllegalArgumentException("Role is invalid");
        } else {
            List<Integer> roleIds = roleRepo.findAll().stream().map(role -> role.getId()).toList();
            List<Integer> invalidReqRoleIds = userDto.getRoles().stream().map(role ->
                    role.getId()).filter(roleId -> !roleIds.contains(roleId)).toList();
            if (!CollectionUtils.isEmpty(invalidReqRoleIds)) {
                throw new IllegalArgumentException("Role is invalid" + invalidReqRoleIds);
            }
        }
    }
}
