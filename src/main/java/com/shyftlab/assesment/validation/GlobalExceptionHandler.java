package com.shyftlab.assesment.validation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.shyftlab.assesment.dtos.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
		// Customize the response or log the exception
		String errorMessage = "An error occurred while processing the request.";
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> errors = bindingResult.getFieldErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());

		ErrorResponseDto response = new ErrorResponseDto("Validation failed", errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}