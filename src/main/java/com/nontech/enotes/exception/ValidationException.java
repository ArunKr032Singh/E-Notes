/**
 * Created By Arun Singh
 * Date:29-01-2025
 * Time:11:03
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.exception;

import java.util.Map;

public class ValidationException extends RuntimeException {
    private Map<String,String> error;

    public ValidationException(Map<String, String> error) {
        super("Validation failed");
        this.error = error;
    }

    public Map<String,String> getErrors()
    {
        return error;
    }
}
