package com.shyftlab.assesment.dtos;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponseDto {
	private final String message;
	private final List<String> errors;

	public ErrorResponseDto(String message, List<String> errors) {
		this.message = message;
		this.errors = errors;
	}

}