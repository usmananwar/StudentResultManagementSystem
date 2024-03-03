package com.shyftlab.assesment.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseDto {

	Long id;
	
	@NotBlank(message = "Course name cannot be empty")
	String name;

}
