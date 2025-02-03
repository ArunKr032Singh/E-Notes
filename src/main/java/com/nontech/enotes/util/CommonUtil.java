/**
 * Created By Arun Singh
 * Date:30-01-2025
 * Time:11:48
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.util;

import com.nontech.enotes.payloads.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    public static String getContentType(String originalFileName) {
        String extension = FilenameUtils.getExtension(originalFileName);
        switch (extension) {
            case "pdf":
                return "application/pdf";
            case "xls":
            case "xlsx":
                return "application/vnd.ms-excel";
            case "txt":
                return "text/plain";
            case "png":
                return "image/png";
            case "jpeg":
            case "jpg":
                return "image/jpeg";
            default:
                return "application/octet-stream"; // Default for unknown file types
        }
    }

    public static String getUrl(HttpServletRequest request) {
        String apiUrl = request.getRequestURL().toString().replace(request.getServletPath(),"");
        System.out.println(apiUrl);
        return apiUrl;
    }
}
