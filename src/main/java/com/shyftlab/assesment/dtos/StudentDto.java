package com.shyftlab.assesment.dtos;

import java.time.LocalDate;

import com.shyftlab.assesment.validation.ValidAge;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDto {

	Long id;

	@NotBlank(message = "First name cannot be empty")
	String firstName;

	@NotBlank(message = "Last name cannot be empty")
	String familyName;

	@ValidAge(message = "Student must be at least {minAge} years old")
	@NotNull(message = "Age cannot be empty")
	LocalDate dob;

	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
	String email;
}
