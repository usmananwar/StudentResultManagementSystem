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
import com.shyftlab.assesment.dtos.StudentDto;
import com.shyftlab.assesment.services.StudentService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<ApiResponseDto> addNewStudent(@Valid @RequestBody StudentDto student) {

		studentService.saveStudent(student);
		ApiResponseDto response = new ApiResponseDto("Student added successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllStudents")
	@ResponseBody
	public ResponseEntity<List<StudentDto>> getAllStudents() {
		return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/deleteStudent")
	public void deleteStudent(Long id) {
		studentService.deleteStudent(id);
	}

}
