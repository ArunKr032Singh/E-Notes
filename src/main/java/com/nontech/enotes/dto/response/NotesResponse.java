/**
 * Created By Arun Singh
 * Date:02-02-2025
 * Time:09:36
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.dto.response;

import com.nontech.enotes.dto.NotesDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class NotesResponse {
    private List<NotesDto> notesDtos;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isFirst;
    private Boolean isLast;
}
