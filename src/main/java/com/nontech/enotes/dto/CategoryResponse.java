package com.nontech.enotes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryResponse {

	private Integer id;
	private String name;
	private String description;

}
