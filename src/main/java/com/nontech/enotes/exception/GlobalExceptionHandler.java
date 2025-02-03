/**
 * Created By Arun Singh
 * Date:28-01-2025
 * Time:11:32
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.exception;

import com.nontech.enotes.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        log.error("GlobalExceptionHandler : handleException :", ex.getMessage());
        return CommonUtil.createErrorResponseMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex) {
        return CommonUtil.createErrorResponseMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("GlobalExceptionHandler : handleResourceNotFoundException :", ex.getMessage());
        return CommonUtil.createErrorResponseMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        log.error("GlobalExceptionHandler : handleValidationException :", ex.getMessage());
        return CommonUtil.createErrorResponse(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistsDataException.class)
    public ResponseEntity<?> handleExistsDataException(ExistsDataException ex) {
        log.error("GlobalExceptionHandler : handleExistsDataException :", ex.getMessage());
        return CommonUtil.createErrorResponseMessage(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("GlobalExceptionHandler : handleHttpMessageNotReadableException :", ex.getMessage());
        return CommonUtil.createErrorResponseMessage(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException ex) {
        log.error("GlobalExceptionHandler : handleFileNotFoundException :", ex.getMessage());
        return CommonUtil.createErrorResponseMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("GlobalExceptionHandler : handleIllegalArgumentException :", ex.getMessage());
        return CommonUtil.createErrorResponseMessage(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
