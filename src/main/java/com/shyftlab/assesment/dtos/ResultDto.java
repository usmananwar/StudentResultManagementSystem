package com.shyftlab.assesment.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResultDto {

	@NotNull(message = "Student can not be empty")
	StudentDto student;

	@NotNull(message = "Course can not be empty")
	CourseDto course;

	@NotBlank(message = "Score can not be empty")
	String score;

}
