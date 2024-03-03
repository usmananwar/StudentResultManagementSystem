package com.shyftlab.assesment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyftlab.assesment.dtos.ApiResponseDto;
import com.shyftlab.assesment.dtos.ResultDto;
import com.shyftlab.assesment.services.ResultService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/result")
public class ResultController {

	@Autowired
	ResultService resultService;

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<ApiResponseDto> addNewCourse(@Valid @RequestBody ResultDto resultDto) {

		resultService.saveResult(resultDto);
		ApiResponseDto response = new ApiResponseDto("Result added successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllResults")
	@ResponseBody
	public ResponseEntity<List<ResultDto>> getAllCourses() {
		return new ResponseEntity<>(resultService.getAllResults(), HttpStatus.OK);
	}

}
