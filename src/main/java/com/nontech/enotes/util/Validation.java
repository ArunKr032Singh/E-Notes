/**
 * Created By Arun Singh
 * Date:29-01-2025
 * Time:10:17
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.util;

import com.nontech.enotes.dto.CategoryDto;
import com.nontech.enotes.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class Validation {
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
}
