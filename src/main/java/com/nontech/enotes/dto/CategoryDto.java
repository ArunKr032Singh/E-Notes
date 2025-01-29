package com.nontech.enotes.dto;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto {

	private Integer id;
//	@NotBlank
//	@Min(value = 10)
//	@Max(value = 100)
	private String name;
//	@NotBlank
//	@Min(value = 10)
//	@Max(value = 100)
	private String description;
//	@NotNull
	private Boolean isActive;
	private Integer createdBy;
	private Date createdOn;
	private Integer updatedBy;
	private Date updatedOn;

}
