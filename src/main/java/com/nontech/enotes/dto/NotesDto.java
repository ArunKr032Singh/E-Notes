/**
 * Created By Arun Singh
 * Date:31-01-2025
 * Time:00:52
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotesDto {

    private Integer id;

    private String title;

    private String description;

    private CategoryDto category;

    private Integer createdBy;

    private Date createdOn;

    private Integer updatedBy;

    private Date updatedOn;

    private FilesDto fileDetails;

    private Boolean isDeleted;

    private LocalDateTime deletedOn;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FilesDto {
        private Integer id;
        private String originalFileName;
        private String displayFileName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryDto{
        private Integer id;
        private String name;
    }

}
