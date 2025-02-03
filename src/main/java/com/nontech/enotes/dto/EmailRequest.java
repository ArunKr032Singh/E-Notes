/**
 * Created By Arun Singh
 * Date:03-02-2025
 * Time:15:56
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailRequest {
    private String to;
    private String subject;
    private String title;
    private String message;

}
