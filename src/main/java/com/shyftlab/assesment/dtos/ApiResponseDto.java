package com.shyftlab.assesment.dtos;

import lombok.Data;

@Data
public class ApiResponseDto {

	private String message;

	public ApiResponseDto(String message) {
		this.message = message;
	}

}
