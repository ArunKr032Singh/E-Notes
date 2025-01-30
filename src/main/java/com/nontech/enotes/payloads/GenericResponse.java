/**
 * Created By Arun Singh
 * Date:30-01-2025
 * Time:11:37
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.payloads;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse {

    private HttpStatus httpStatus;
    private String status;
    private String message;
    private Object data;

    public ResponseEntity<?> create() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", status);
        map.put("message", message);
        if (!ObjectUtils.isEmpty(data)) {
            map.put("data", data);
        }
        return new ResponseEntity<>(map, httpStatus);
    }
}
