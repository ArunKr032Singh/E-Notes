/**
 * Created By Arun Singh
 * Date:30-01-2025
 * Time:11:48
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.util;

import com.nontech.enotes.payloads.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonUtil {
    public static ResponseEntity<?> createBuildResponse(Object data, HttpStatus status) {
        GenericResponse response = GenericResponse.builder()
                .httpStatus(status)
                .status("Success")
                .message("Success")
                .data(data)
                .build();
        return response.create();
    }

    public static ResponseEntity<?> createBuildResponseMessage(String message, HttpStatus status) {
        GenericResponse response = GenericResponse.builder()
                .httpStatus(status)
                .status("Success")
                .message(message)
                .build();
        return response.create();
    }

    public static ResponseEntity<?> createErrorResponse(Object data, HttpStatus status) {
        GenericResponse response = GenericResponse.builder()
                .httpStatus(status)
                .status("Failed")
                .message("Failed")
                .data(data)
                .build();
        return response.create();
    }

    public static ResponseEntity<?> createErrorResponseMessage(String message, HttpStatus status) {
        GenericResponse response = GenericResponse.builder()
                .httpStatus(status)
                .status("Failed")
                .message(message)
                .build();
        return response.create();
    }
}
