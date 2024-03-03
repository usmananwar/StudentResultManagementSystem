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
import com.shyftlab.assesment.dtos.CourseDto;
import com.shyftlab.assesment.services.CourseService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	CourseService courseService;

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<ApiResponseDto> addNewCourse(@Valid @RequestBody CourseDto courseDto) {

		courseService.saveCourse(courseDto.getName());
		ApiResponseDto response = new ApiResponseDto("Course added successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllCourses")
	@ResponseBody
	public ResponseEntity<List<CourseDto>> getAllCourses() {
		return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
	}
	
	@GetMapping("/deleteCourse")
	public void deleteCourse(Long id) {
		courseService.deleteCourse(id);
	}

}
