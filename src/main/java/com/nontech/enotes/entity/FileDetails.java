/**
 * Created By Arun Singh
 * Date:31-01-2025
 * Time:12:08
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FileDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uploadedFileName;
    private String originalFileName;
    private String displayFileName;
    private String path;
    private Long fileSize;

}
